window.onload = function(){
    new Vue({
        el: '#app',
        data() {
            return{
               loginUser : null
            }
        },
        mounted () {

            console.log("test");

            axios
                .get('/user/getUserInfoByUsername')
                .then( (response) => {
                    this.loginUser = response.data;
                })
                .catch(function (error) {
                    console.log(error)
                })
        }
    })
}
