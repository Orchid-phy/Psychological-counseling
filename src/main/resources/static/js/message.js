    //发送人的id
    var userId = "0";
    //接受者的id
    var toUserId = "0";
    //发送消息
    flag = "success";

    $(function () {

        //获取登录用户信息
        $.ajax({
            type: "get",
            dataType: "JSON",
            async:false,
            url: "/user/getUserInfoByUsername",
            success: function(data) {

                if (data.userId != null){
                    $("#selfCenter").css("display", "");
                }

                if (data.role.roleName === "咨询师"){
                    $("#chatLi").css("display", "");
                }

                //打开socket， 如果不支持socket则提示消息
                openSocket(data.userId);

            }
        });

        //消息滚动条，自动在底部，看到最新的消息
        $('.messageDiv')[0].scrollTop =$('.messageDiv')[0].scrollHeight;

    });

    layui.use('layer', function(){ //独立版的layer无需执行这一句
        var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句

        //触发事件
        var active = {
            offset: function(othis){
                var type = othis.data('type')
                    ,text = othis.text();

                layer.open({
                    type: 1
                    ,offset: type
                    ,id: 'layerDemo'+type
                    ,content: $("#messageLayer")
                    ,area:["520px","480px"]
                    ,btn: ['发送', '关闭']
                    ,btnAlign: 'c' //按钮居中
                    ,shade: 0 //不显示遮罩
                    ,maxmin: true
                    ,yes: function(){
                        sendMessage();
                    }
                    ,btn2 : function () {
                        layer.closeAll();
                    }
                });
            }
        };

        $('body').on('click', '.chat', function(){

            if (flag === "success"){
                var othis = $(this), method = othis.data('method');
                active[method] ? active[method].call(this, othis) : '';
            }

        });

    });

    var socket;
    function openSocket(loginUserId) {

        if(typeof(WebSocket) == "undefined") {
            alert("您的浏览器不支持WebSocket,请使用高版本的浏览器哦！");
            flag = "error";
        }else{
            //实现化WebSocket对象，指定要连接的服务器地址与端口  建立连接
            //var socketUrl="${request.contextPath}/im/"+$("#userId").val();
            var socketUrl="http://localhost:8080/imserver/" + loginUserId;
            socketUrl=socketUrl.replace("https","ws").replace("http","ws");
            // console.log(socketUrl);
            if(socket!=null){
                socket.close();
                socket=null;
            }
            socket = new WebSocket(socketUrl);
            //打开事件
            socket.onopen = function() {
                // console.log("websocket已打开");
                // socket.send("这是来自客户端的消息" + location.href + new Date());
            };
            //获得消息事件
            socket.onmessage = function(msg) {

                //发现消息进入    开始处理前端触发逻辑
               if(msg != null){
                   message = $.parseJSON(msg.data);

                   var myDate = new Date();

                   var html = "<table>\n" +
                       "                <tr>\n" +
                       "                    <td style=\"font-size: 12px; text-align: right;\">" + myDate.toLocaleString( ) + "</td>\n" +
                       "                    <td rowspan=\"2\" style=\"width: 32px;\">\n" +
                       "                        <img style=\"width: 30px; height: 30px;\" src=\"../updateFile/img/avatar/baibai.jpg\">\n" +
                       "                    </td>\n" +
                       "                </tr>\n" +
                       "                <tr>\n" +
                       "                    <td style=\"text-align: right;\">" + message.contentText + "</td>\n" +
                       "                </tr>\n" +
                       "            </table>"

                   //将消息显示
                   $("#message").append(html);

                   //有消息提示
                    $(".chat").css("background-color", "red");

                   //接收消息用户id
                   toUserId = message.fromUserId;
               }

            };
            //关闭事件
            socket.onclose = function() {
                console.log("websocket已关闭");
            };
            //发生了错误事件
            socket.onerror = function() {
                console.log("websocket发生了错误");
            }
        }
    }
    function sendMessage() {
        if(typeof(WebSocket) == "undefined") {
            alert("您的浏览器不支持WebSocket");
        }else {
            socket.send('{"toUserId":"'+ toUserId +'","contentText":"'+$("#contentText").val()+'"}');

            var myDate = new Date();

            var html = "<table>\n" +
                "                <tr>\n" +
                "                    <td rowspan=\"2\"style=\"width: 32px;\">\n" +
                "                        <img style=\"width: 30px; height: 30px;\" src=\"../updateFile/img/avatar/baibai.jpg\">\n" +
                "                    </td>\n" +
                "                    <td style=\"font-size: 12px;\">" + myDate.toLocaleString( ) + "</td>\n" +
                "                </tr>\n" +
                "                <tr>\n" +
                "                    <td>" + $("#contentText").val() + "</td>\n" +
                "                </tr>\n" +
                "            </table>";

            $("#message").append(html);

            $(".contentInput").val("");
        }
    }
