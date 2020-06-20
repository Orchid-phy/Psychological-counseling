window.onload = function(){
    new Vue({
        el: '#myHome',
        data() {
            return{
                //咨询师信息
                counselorPageInfo: null,
                //咨询师成员
                // counselors: null,
                //心理咨询师
                counselorList: null,

                //刷新页面数据
                isRouterAlive : true,

                //是否选中所有
                isAll : false,

                //登录用户信息
                loginUser : null,

                //预约日期
                reservationDate : null,

                //预约时间
                reservationTime : "08:00",

                //留言
                leaveMessage : ""

            }
        },
        mounted () {

            axios
                .get('/user/getUserInfoByUsername')
                .then( (response) => {

                    this.loginUser = response.data;

                })
                .catch(function (error) {
                    console.log(error)
                });

            this.getCounselorByGroup();

        },
        methods : {

            reload (){
                this.isRouterAlive = false
                this.$nextTick(function(){
                    this.isRouterAlive = true
                })
            },

            //获取同一个团队的咨询师
            getCounselorByGroup : function () {

                axios.get('/user/getCounselorInfo')
                    .then((response)=>{

                        this.counselorList = response.data.data;

                    })

            },

            //分页所有咨询师，按照注册时间排序
            getAllCounselor : function (pageNum){
                axios.get('/user/getAllCounselor',
                    {
                        params: {
                            pageNum: pageNum
                        }

                    })
                    .then((response)=>{

                        this.counselorPageInfo = response.data.data
                        this.counselorList = this.counselorPageInfo.list;
                        this.isAll = true;
                        this.reload();

                    })
            },
            //用户发起预约
            postReservation : function (userId) {

                time = this.reservationDate.concat(" ").concat(this.reservationTime);


                axios.get('/addConsultingRecords',
                    {
                        params: {
                            time: time,
                            counselorId : userId
                        }
                    })
                    .then((response)=>{

                        if (response.data.code == 200){
                            alert("预约成功！");
                        }else {
                            alert(response.data.msg);
                        }

                    })
            }

        }
    })
}
