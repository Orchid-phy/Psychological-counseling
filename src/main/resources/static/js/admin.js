    new Vue({
        el: "#app",
        data() {

            return{
                //用户信息
                users : null,//团队数据
                userPageInfo: null,
                //当前页
                userPageNum : null,
                //总页数
                userPages : null,
                //共多少条记录
                userTotal : null,
                userHasPreviousPage : null,
                userPrePage : null,
                userHasNextPage : null,
                userNextPage : null,
                searchUserId : null,//用户id 查询参数
                searchUsername : null,//用户名，查询参数

                loginUser : null,

            }

        },
        mounted() {

            axios
                .get('/user/getUserInfoByUsername')
                .then( (response) => {
                    this.loginUser = response.data;

                    // //验证码
                    // this.phone = this.loginUser.phone;
                    // this.email = this.loginUser.email;
                    // this.IDCard = this.loginUser.idCard;
                    // this.sex = this.loginUser.sex;
                    // this.age = this.loginUser.age;
                    // this.realName = this.loginUser.realName;
                    // this.username = this.loginUser.username;
                    // this.userId = this.loginUser.userId;

                })
                .catch(function (error) {
                    console.log(error)
                })

            this.getUser(1);

        },
        methods: {
            //管理员创建的团体的用户信息
            getUser : function (pageNum) {

                axios
                    .get('/user/getUserByAdmin',{
                        params:{
                            pageNum : pageNum,
                            searchUserId : this.searchUserId,
                            searchUserName : this.searchUserName
                        }
                    })
                    .then( (response) => {

                        if (response.data.code != 200){
                            alert(response.data.msg);
                        }

                        userPageInfo = response.data.data.pageInfo;

                        this.users = userPageInfo.list;

                        this.userPageNum = userPageInfo.pageNum;

                        this.userPages = userPageInfo.pages;

                        this.userTotal = userPageInfo.total;

                        this.userHasPreviousPage = userPageInfo.hasPreviousPage;

                        this.userPrePage = userPageInfo.prePage;

                        this.userHasNextPage = userPageInfo.hasNextPage;

                        this.userNextPage = userPageInfo.nextPage;

                        this.searchUserId = response.data.data.searchUserId;

                        this.searchUserName = response.data.data.searchUserName;

                    })
                    .catch(function (error) {
                        console.log(error)
                    })

            },
            //删除用户
            delUser : function (userId) {

                axios.get('/user/delUser?userId='+userId)
                    .then(function (response) {
                        if (response.data.code == 200){
                            alert("删除成功")
                        }else {
                            alert(response.data.msg)
                        }
                    })

            }
        }

    })


