
window.onload = function(){
    new Vue({
        el: '#app',
        data() {
            return{
                loginUser : null,
                //验证码
                validCode : null,
                phone : null,
                email : null,
                IDCard : null,
                sex : null,
                age : null,
                realName : null,
                username : null,
                userId : null,

                //刷新页面数据
                isRouterAlive : true,

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
        },
        methods: {

            reload (){
                this.isRouterAlive = false
                this.$nextTick(function(){
                    this.isRouterAlive = true
                })
            },
            // 提交表单，修改用户信息
            upUserInfo: function () {
                axios.post('/user/upUserInfo', {
                    userId: this.userId,
                    username: this.username,
                    realName: this.realName,
                    age: this.age,
                    sex: this.sex,
                    idCard: this.IDCard,
                    email: this.email,
                    phone: this.phone,
                    validCode: this.validCode
                })
                    .then(function (response) {

                        if (response.data.code != 200){
                            alert(response.data.msg);
                        }

                    })
                    .catch(function (error) {
                        console.log(error);
                    });

            },

            // 取消预约
            delRecord: function () {

                axios.get('/delRecord', {
                    params: {
                        id: this.$refs.reId.value
                    }
                })
                    .then(function (response) {

                        // 取消成功就刷新页面
                        if (response.data.code == 200){

                            axios
                                .get('/getNewCounsellingRecord')
                                .catch(function (error) {
                                    console.log(error)
                                });

                            this.reload();
                            alert("取消成功。")

                        }else {
                            alert(response.data.msg);
                        }

                    })
                    .catch(function (error) {
                        console.log(error);
                    });

            }

        }
    })
}
