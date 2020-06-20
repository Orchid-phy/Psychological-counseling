
window.onload = function(){
    new Vue({
        el: '#app',
        data() {
            return{

                groups : null,//团队数据
                pageInfo: null,
                //当前页
                groupPageNum : null,
                //总页数
                groupPages : null,
                //共多少条记录
                groupTotal : null,
                groupHasPreviousPage : null,
                groupPrePage : null,
                groupHasNextPage : null,
                groupNextPage : null,

                papers : null,//试题数据
                paperPageInfo : null,
                paperPageNum : null,
                paperPages : null,
                paperTotal : null,
                paperHasPreviousPage : null,
                paperPrePage : null,
                paperHasNextPage : null,
                paperNextPage : null,

                paperId : null,//试题查询参数
                typeName : null,//试题查询参数
                searchId : null,//团体id 查询参数
                searchGroupName : null,//团体名字，查询参数

                //团队复选框
                checkGroups: [],
                //试题复选框
                checkPapers : [],
                //任务截止日期
                stopDate : null,


                //添加团体
                groupName : null,
                affiliation : null,
                description : null,

                //试题信息
                topicPageInfo : null,
                topics : null,

                //查询参数
                topicId : null,

                //获得任务参数
                taskGroupId : null,
                taskPaperId : null,
                taskPageInfo : null,
                tasks : null,
                //用户信息
                userId: null,
                username: null,
                realName: null,
                age: null,
                sex: null,
                IDCard: null,
                email: null,
                phone: null,
                validCode: null,
                reId: null,
                img: null,
                roleName: null,


                //选中团体
                checkGroupIds : [],
                isCheckedGroupAll: false,

                //登录用户信息
                loginUser : null,

                //用户详细的咨询记录
                userRecordsPageInfo : null,
                //查询记录条件用户名
                recordUsername : null,

                //刷新页面数据
                isRouterAlive : true,

                //用户咨询记录
                userRecords : null,

                //用户测试记录
                userEvaRecordsPageInfo : null,
                userEvaRecords : null,

                //申请记录用户id
                applyUserId : null,
                applyPageInfo : null,
                applys : null,

                //咨询师申请记录用户id
                counselorApplyUserId : null,
                counselorApplyPageInfo : null,
                counselorApplys : null

            }
        },
        mounted () {

            axios
                .get('/user/getUserInfoByUsername')
                .then( (response) => {
                    this.loginUser = response.data;
                    // console.log(this.loginUser);
                })
                .catch(function (error) {
                    console.log(error)
                });

            //渲染页面团队数据
            // this.getGroup(1);

            //渲染页面试题数据
            this.getGroupByAdmin(1);

            //渲染页面试题数据
            this.getPaper(1);

            //获得所有试题数据
            this.getTopic(1);

            this.getApplyRecord();

            this.getCounselorApplyRecord();

        },
        methods: {

            reload (){
                this.isRouterAlive = false
                this.$nextTick(function(){
                    this.isRouterAlive = true
                })
            },

            //发布任务
            postTask : function () {
                axios
                    .post('/postTask',{
                        params:{
                            groups : this.checkGroups,
                            papers : this.checkPapers,
                            stopDate : this.stopDate
                        }

                    })
                    .then(function (response) {

                        alert(response.data.msg);

                    })
                    .catch(function (error) {
                        console.log(error);
                    });

            },

            //获得任务信息
            getTask:function(pageNum){

                axios.get('/getTask',{
                    params:{
                        pageNum : pageNum,
                        groupId : this.taskGroupId,
                        paperId : this.taskPaperId

                    }
                })
                    .then(function (response) {

                    })

            },

            //创建团体
            addGroup : function () {

                axios.get('/addGroup',
                    {
                        params:{
                            groupName : this.groupName,
                            affiliation : this.affiliation,
                            description : this.description
                        }
                    })
                    .then( (response) => {

                        alert(response.data.msg);

                        this.getGroupByAdmin();

                        this.reload();

                    })

            },
            //删除团体
            delGroup : function (groupId) {

                var r = confirm("是否确定解散？");
                if (r == true)
                {
                    axios.get('/delGroup?groupId='+groupId,{
                        params:{
                            groupId : this.groupId
                        }
                    })
                        .then( (response) => {

                            if (response.data.code == 200){
                                this.getGroupByAdmin(1);
                                this.reload();
                            } else {
                                alert(response.data.msg)
                            }

                        })
                }
            },
            // //获得团队信息
            // getGroup : function (pageNum, event) {
            //
            //         // 获取团队信息
            //     axios
            //         .get('/getGroup',{
            //             params:{
            //                 pageNum : pageNum,
            //                 searchId : this.searchId,
            //                 searchGroupName : this.searchGroupName
            //             }
            //     })
            //         .then( (response) => {
            //
            //             pageInfo = response.data.data.pageInfo;
            //
            //             this.groups = pageInfo.list;
            //
            //             this.groupPageNum = pageInfo.pageNum;
            //
            //             this.groupPages = pageInfo.pages;
            //
            //             this.groupTotal = pageInfo.total;
            //
            //             this.groupHasPreviousPage = pageInfo.hasPreviousPage;
            //
            //             this.groupPrePage = pageInfo.prePage;
            //
            //             this.groupHasNextPage = pageInfo.hasNextPage;
            //
            //             this.groupNextPage = pageInfo.nextPage;
            //
            //             this.searchId = response.data.data.searchId;
            //
            //             this.searchGroupName = response.data.data.searchGroupName;
            //
            //         })
            //         .catch(function (error) {
            //             console.log(error)
            //         })
            //
            // },
            //根据管理员获取用户信息
            getGroupByAdmin : function(pageNum){

                axios
                    .get('/getGroupByAdmin',{
                        params:{
                            pageNum : pageNum,
                            searchId : this.searchId,
                            searchGroupName : this.searchGroupName
                        }
                    })
                    .then( (response) => {

                        this.pageInfo = response.data.data.pageInfo;

                        this.groups = this.pageInfo.list;

                        this.groupPageNum = this.pageInfo.pageNum;

                        this.groupPages = this.pageInfo.pages;

                        this.groupTotal = this.pageInfo.total;

                        this.groupHasPreviousPage = this.pageInfo.hasPreviousPage;

                        this.groupPrePage = this.pageInfo.prePage;

                        this.groupHasNextPage = this.pageInfo.hasNextPage;

                        this.groupNextPage = this.pageInfo.nextPage;

                        this.searchId = response.data.data.searchId;

                        this.searchGroupName = response.data.data.searchGroupName;

                    })
                    .catch(function (error) {
                        console.log(error)
                    })
            },
            // 获取试题信息
            getPaper : function (pageNum) {
                axios
                    .get('/getPaper',{
                        params:{
                            pageNum : pageNum,
                            paperId : this.paperId,
                            typeName : this.typeName
                        }
                    })
                    .then( (response) => {

                        paperPageInfo = response.data.data.paperPageInfo;

                        this.papers = paperPageInfo.list;

                        this.paperPageNum = paperPageInfo.pageNum;

                        this.paperTotal = paperPageInfo.total;

                        this.paperHasPreviousPage = paperPageInfo.hasPreviousPage;

                        this.paperPrePage = paperPageInfo.prePage;

                        this.paperHasNextPage = paperPageInfo.hasNextPage;

                        this.paperNextPage = paperPageInfo.nextPage;

                        this.paperId = response.data.data.paperId;

                        this.typeName = response.data.data.typeName;

                    })
                    .catch(function (error) {
                        console.log(error)
                    });

            },
            //根据试卷id获取所有试题信息
            getTopic:function (pageNum, paperId) {

                    axios.get('/getTopicByPaperId', {
                        params: {
                            pageNum: pageNum,
                            paperId: paperId
                        }
                    }).then((response) => {

                        this.topicPageInfo = response.data.data.topicPageInfo;

                        this.topics = this.topicPageInfo.list;

                    })

            },
            //根据试卷id获取所有试题信息
            getTopicByTopicId:function (pageNum, topicId) {

                axios.get('/getTopicByTopicId', {
                    params: {
                        pageNum: pageNum,
                        topicId: topicId
                    }
                }).then((response) => {

                    this.topicPageInfo = response.data.data.topicPageInfo;

                    this.topics = this.topicPageInfo.list;

                    this.topicId = response.data.data.topicId;

                })

            },
            //获得所有任务列表
            getTask: function (pageNum) {

                axios.get('/getTask',{
                    params : {
                        pageNum : pageNum,
                        groupId : this.taskGroupId,
                        paperId : this.taskPaperId
                    }
                })
                    .then( (response)=>{

                        this.taskPageInfo = response.data.data.taskPageInfo;

                        this.tasks = this.taskPageInfo.list;

                        this.taskGroupId = response.data.data.groupId;

                        this.taskPaperId = response.data.data.paperId;
                    } )

            },
            //根据用户名获取所有的详细咨询记录
            getConsultingRecordByUserId: function (pageNum) {

                axios.post('/getConsultingRecordByUserId',{
                        pageNum : pageNum,
                        username : this.recordUsername
                })
                    .then( (response)=>{

                        this.userRecordsPageInfo = response.data.data.userRecordsPageInfo;

                        this.userRecords = this.userRecordsPageInfo.list;

                        this.recordUsername = this.userRecordsPageInfo.list[0].user.username;

                        this.reload();

                    } )

            },
            //根据用户名获取所有的详细测试记录
            getEvaRecordByUsername: function (pageNum) {

                axios.post('/getEvaRecordByUsername',{
                    pageNum : pageNum,
                    username : this.recordUsername
                })
                    .then( (response)=>{

                        this.userEvaRecordsPageInfo = response.data.data.userEvaRecordsPageInfo;

                        this.userEvaRecords = this.userEvaRecordsPageInfo.list;

                        this.recordUsername = this.userEvaRecordsPageInfo.list[0].user.username;

                        this.reload();

                    } )

            },
            //根据用户id获取申请记录
            getApplyRecord: function (pageNum) {

                axios.post('/getApply',{
                    pageNum : pageNum,
                    username : this.applyUserId
                })
                    .then( (response)=>{

                        this.applyPageInfo = response.data.data;

                        this.applys = this.applyPageInfo.list;

                        // this.reload();

                    } )

            },
            //根据用户名id获取咨询师申请记录
            getCounselorApplyRecord: function (pageNum) {

                axios.post('/getCounselorApply',{
                    pageNum : pageNum,
                    username : this.applyUserId
                })
                    .then( (response)=>{

                        this.counselorApplyPageInfo = response.data.data;


                        this.counselorApplys = this.counselorApplyPageInfo.list;

                        // console.log(this.counselorApplys);

                        // this.reload();

                    } )

            }

        }

    })
};
