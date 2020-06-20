window.onload = function(){
    new Vue({
        el: '#confide',
        data() {
            return{
                //判断是否是咨询师
                isCounselor : false,
                //动态信息
                confidePageInfo : null,
                confides : null,
                //文章信息
                articlePageInfo: null,
                articles: null,
                //文章中的图片信息
                img: [],
                //登录用户的信息
                loginUser : null,
                //用户输入的冬天
                commentsIn : null,

                //用户id查询动态
                userConfidePageInfo : null,
                userConfides : null,
            }
        },
        mounted () {

            this.getConfide();

            this.getArticle();

            this.getUserRole();

            if (this.userConfidePageInfo == null){
                this.getConfideByUserId();
            }

            axios
                .get('/user/getUserInfoByUsername')
                .then( (response) => {

                    this.loginUser = response.data;

                })
                .catch(function (error) {
                    console.log(error)
                })

        },
        methods : {

            //查询用户的角色信息
            getUserRole : function(){

                axios.get('/user/getUserIsCounselor').then( (response)=>{
                    if(response.data.code == 200){
                        this.isCounselor = true;
                    }
                });

            },
            //查询所有的动态消息
            getConfide : function (pageNum) {

                axios.get('/getConfideAndComment',{
                    params:{
                        pageNum : pageNum
                    }
                })
                    .then((response)=>{

                        this.confidePageInfo = response.data.data;

                        this.confides = this.confidePageInfo.list;

                        for (var i = 0; i < this.confides.length; i++){

                            if (this.confides[i].img != null){

                                this.confides[i].img = this.confides[i].img.split(",");

                            }
                        }

                    })
            },
            //根据用户id查询所有的动态消息
            getConfideByUserId : function (pageNum) {

                axios.get('/getConfideByUserId',{
                    params:{
                        pageNum : pageNum
                    }
                })
                    .then((response)=>{

                        this.userConfidePageInfo = response.data.data;

                        this.userConfides = this.userConfidePageInfo.list;

                        for (var i = 0; i < this.userConfides.length; i++){

                            if (this.userConfides[i].img != null){

                                this.userConfides[i].img = this.userConfides[i].img.split(",");

                            }
                        }

                    })
            },
            // 分页查询文章信息
            getArticle : function (pageNum) {

                axios.get('/getArticleByAuthorName',{
                    params:{
                        pageNum : pageNum
                    }
                })
                    .then((response)=>{

                        this.articlePageInfo = response.data.data;

                        this.articles = this.articlePageInfo.list;

                        var img = this.articles.img;
                        //处理文章中的图片信息
                        if ( img != null){
                            this.imgs = img.split(",");
                        }
                        
                    })

            },
            //添加动态消息
            addComments : function (event) {

                axios.post('/addComment',{
                    userId : this.loginUser.userId ,
                    confideId : $(".commentButton").attr("confideId"),
                    comments : this.commentsIn
                })
                    .then((response)=>{

                        if (response.data.code == 200){

                            var html = "<footer class=\"post-footer d-flex align-items-center\" v-for=\"comment in confide.commentList\">\n" +
                                "                        <div style=\"padding-left: 8px\" class=\"comments meta-last\">"+ this.commentsIn +"</div>\n" +
                                "                    </footer>";

                            var className = event.target.className;

                            $("." + className ).parent().after(html);

                            $(".commentsIn").val("");

                        }

                    })
            }

        }
    })
}
