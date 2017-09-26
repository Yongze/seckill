//存放主要交互逻辑js代码
//javascript module
//seckill.detail.init(params);
var seckill = {
    //封装秒杀相关ajax的url
    URL: {
        now: function () {
           return  "/seckill/time/now";
        },
        exposer:function (seckillId) {
            return "/seckill/" + seckillId + "/exposer";
        },
        execution:function (seckillId, md5) {
            return "/seckill/" + seckillId + "/" + md5 + "/execution";
        }
    },//处理秒杀逻辑
    handleSeckillkill:function(seckillId,seckillbox){
        seckillbox.hide().html('<button class="btn btn-primary btn-lg" id="killbtn">Start seckill</button>');//button
        $.post(seckill.URL.exposer(seckillId),{},function (result) {
            //在回调函数中，执行交互流程
            if (result && result['success']){
                var exposer = result['data'];
                if (exposer['exposed']){
                    //start seckill
                    //获取秒杀地址
                    var killurl = seckill.URL.execution(seckillId,exposer['md5']);
                    console.log("killUrl: " + killurl);
                    //只绑定一次点击事件
                    $('#killbtn').one('click',function () {
                        //执行秒杀哦请求的操作
                        //1.先禁用按钮
                        $(this).addClass('disabled');
                        //2.发送秒杀请求执行秒杀
                        $.post(killurl,{},function (result) {
                            if (result && result['data']){
                                var seckillresult = result['data'];
                                var stateInfo = seckillresult['stateInfo'];
                                //显示秒杀结果
                                seckillbox.html('<span class="label label-success">' + stateInfo + '</span>');
                            }
                        });
                    });
                    seckillbox.show();
                }else{
                    //未开启秒杀
                    var now = exposer['now'];
                    var start = exposer['start'];
                    var end = exposer['end'];
                    //重新计算计时逻辑
                    seckill.countdowns(seckillId, now, start, end);
                }
            }else{
                console.log('result: ' + result);
            }
        })
    },
    //验证手机号
    validatePhone: function (phone) {
        if (phone && phone.length == 11 && !isNaN(phone)) {
            return true;
        }
        return false;
    },
    countdowns:function (seckillId, now, start, end) {
        //时间判断
        var seckillbox = $('#seckill-box');
        if (now > end){
            //秒杀结束
            seckillbox.html('Seckill End.');
        }else if(now < start){
            //秒杀未开始,计时事件绑定
            //original code with date: Thu Jun 14 2018 15:24:08 GMT+1000 (Local Standard Time)
            //var killTime = new Date(start + 1000);
            //console.log("The killTime time is: " + killTime);
            //console.log("The start time is: " + start);
            seckillbox.countdown(start, function (event) {
                var format = event.strftime('Seckill countdown: %Ddays %Hhours %Mmins %Ss');
                seckillbox.html(format);
            //    时间完成后回调时间
            }).on('finish.countdown',function () {
                //获取秒杀地址，控制实现逻辑，执行秒杀
                seckill.handleSeckillkill(seckillId,seckillbox);
            });
        }else{
            //秒杀结束
            seckill.handleSeckillkill(seckillId,seckillbox);
        }
    },
    //详情页秒杀逻辑
    detail: {
        //详情页初始化
        init: function (params) {
            //用户手机验证和登录，计时交互
            //规划交互流程
            //在cookie中查找手机号
            var killPhone = $.cookie('killPhone');
            console.log("Get kill phone from cookie: " + killPhone);//TODO
            var startTime = params['startTime'];
            var endTime = params['endTime'];
            var seckillId = params['seckillId'];
            //验证手机号
            if (!seckill.validatePhone(killPhone)) {
                //绑定phone
                //控制输出
                var killPhoneModel = $('#killPhoneModel');
                //显示弹出层
                killPhoneModel.modal({
                    show: true,//显示弹出层
                    backdrop: 'static',//禁止位置关闭
                    keyboard: false//关闭键盘事件
                });
                $('#killPhoneBtn').click(function () {
                    var inputPhone = $('#killPhoneKey').val();
                    console.log("Phone number: " + inputPhone);//TODO
                    if (seckill.validatePhone(inputPhone)) {
                        //电话写入cookie
                        $.cookie('killPhone', inputPhone, {expires: 7, path: '/seckill'});
                        //刷新页面
                        window.location.reload();
                    } else {
                        $('#killPhoneMessage').hide().html('<label class="label label-danger">Incorrect phone number</label>').show(300);
                    }
                });

            }
            //已经登录
            //计时交互
            $.get(seckill.URL.now(),{},function(result){
                if(result && result['success']){
                    var notTime = result['data'];
                    //时间判断,计时交互
                    seckill.countdowns(seckillId,notTime,startTime,endTime);
                }else{
                    console.log("result " + result);
                }
            });
        }
    }
}