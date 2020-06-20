window.onload = function(){
    new Vue({
        el: '#myHome',
        data() {
            return{
                papersTop : null,
                checkType : null,
                paperPageInfo : null,
                papers : null,
                allType : null,
                typeName : null,
                //用户选择测试的试题集合
                testPaper : null,
                //用来保存测试题的id, 获得第一次页面跳转的paperId
                testPaperId : null,
                //题目
                testTopic : null,
                //测试题的选项
                testTopicOptions : null,
                //详细测试试题信息
                testTopicPageInfo : null,
                //用户做测试题选中的答案
                checkTopic : null,
                topicSumScore : 0,
                //试题的详细信息
                paper : null,

                //登录用户
                loginUser : null

            }
        },
        mounted () {

            axios
                .get('/user/getUserInfoByUsername')
                .then( (response) => {
                    this.loginUser = response.data;

                    //验证码
                    this.phone = this.loginUser.phone;
                    this.email = this.loginUser.email;
                    this.IDCard = this.loginUser.idCard;
                    this.sex = this.loginUser.sex;
                    this.age = this.loginUser.age;
                    this.realName = this.loginUser.realName;
                    this.username = this.loginUser.username;
                    this.userId = this.loginUser.userId;

                })
                .catch(function (error) {
                    console.log(error)
                })

            //查询测试最多的前三项
            this.getPaperTop();

            //根据类型查询
            this.getPaperByType();

            //获取所有的数据类型
            this.getTopicType();

            //获取测试题详细
            this.getTestByPaperId();

        },
        methods : {

            //获取测试人数最多的前三种测试
            getPaperTop : function () {

                axios.get('/getPaperTop')
                    .then((response)=>{

                        this.papersTop = response.data.data;

                    })

            },
            //跳转至更多测试页面
            goToPaper: function () {

                window.location.href="paper.html";

            },
            //根据类型获取试题信息
            getPaperByType : function (typeName, pageNum) {

                axios.get('/getPaperByType',{
                    params : {
                        typeName : typeName,
                        pageNum : pageNum
                    },
                    headers : {
                        "Content-type" : "application/x-www-form-urlencoded;charset=UTF-8"
                    }
                }).then( (response) =>{
                    this.paperPageInfo = response.data.data.pageInfo;
                    this.papers = this.paperPageInfo.list;

                    this.typeName = response.data.data.typeName;

                })
            },
            //获取所有试题类型
            getTopicType : function () {
                axios.get('/getTopicType').then((response) =>{
                    this.allType = response.data.data;
                })
            },
            // //跳转至测试页面
            // toTest : function(paperId) {
            //
            //     //保存用户选择的paperId
            //     this.testPaperId = paperId;
            //
            //     // //调用查询函数
            //     this.$options.methods.getTestByPaperId(1, paperId);
            //
            // },
            //用户点击测试，根据选择试题id，查询试题选项及其答案
            getTestByPaperId : function (pageNum) {

                //计算结果总分
                this.topicSumScore = this.topicSumScore + this.checkTopic;

                axios.get('/getTopicByPaperId', {
                    params:{
                        pageNum : pageNum,
                        pageSize : 1
                    }
                }).then((response) =>{

                    if (response.data.code == 200){

                        this.testTopicPageInfo = response.data.data.topicPageInfo;

                        this.testTopic = this.testTopicPageInfo.list[0];

                        this.testTopicOptions = this.testTopic.optionsList;

                        this.paper = response.data.data.paper;
                        
                    }

                })

            },
            //根据总分查询结果
            getPaperResult : function (paperId) {

                //计算结果总分
                this.topicSumScore = this.topicSumScore + this.checkTopic;

                axios.get('/getPaperResult', {
                    params:{
                        paperId : paperId,
                        score: this.topicSumScore
                    }
                }).then((response) =>{

                    if (response.data.code == 200){

                        var result;

                        var paperResult = response.data.data;

                        for (var i = 0; i < paperResult.length; i++) {

                            var score = paperResult[i].scoreInterval.split("-");

                            if (this.topicSumScore <= score[1] && this.topicSumScore > score[0]){
                                result = paperResult[i].result;
                                break;
                            }

                        }

                        $("#testContent").html("<p style='font-size: 20px; margin: 20px 0px;'>分数为：" + this.topicSumScore + "</p><label>" + result + "</label>");
                        $("#testFooter").css("display", "none");

                    }

                })

            }

        }
    });
};
