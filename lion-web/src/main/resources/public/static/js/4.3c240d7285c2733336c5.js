webpackJsonp([4],{537:function(n,t,a){a(558);var o=a(213)(a(548),a(566),"data-v-4aea9498",null);n.exports=o.exports},548:function(n,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0}),t.default={components:{"remote-js":{render:function(n){return n("script",{attrs:{type:"text/javascript",src:this.src}})},props:{src:{type:String,required:!0}}}},data:function(){return{man_verified:!1,mobile:"",password:"",loginErrorMsg:""}},methods:{doLogin:function(){return this.mobile?this.password?void this.$axios.post("/api/user/login",{mobile:this.mobile,password:this.password}).then(function(n){n.data.data?this.$router.push({name:"addWeightRecord"}):this.loginErrorMsg="用户名或密码错误"}.bind(this)):void(this.loginErrorMsg="请输入密码"):void(this.loginErrorMsg="请输入用户名/邮箱/手机号")}}}},552:function(n,t,a){t=n.exports=a(532)(!1),t.push([n.i,'\n@charset "UTF-8";\n/*登录页头*/\n.login-header[data-v-4aea9498] {\n  height: 60px;\n  background-color: #fff;\n  padding: 20px 0;\n}\n.login-header .logo[data-v-4aea9498] {\n    background-color: #fff;\n    width: 1200px;\n    margin: 0 auto;\n    position: relative;\n}\n.login-header .logo .logo_img[data-v-4aea9498] {\n      background: url(https://lomoye.image.alimmdn.com/hours/1bcb6d81a28e4fcf86da51e375d28fd9) no-repeat;\n      display: inline-block;\n      width: 220px;\n      height: 52px;\n      background-size: 100%;\n}\n\n/*登录身体*/\n.login-body[data-v-4aea9498] {\n  position: relative;\n  height: 600px;\n  margin-bottom: 15px;\n  /*海报广告*/\n  /*登录框*/\n}\n.login-body .login-banner[data-v-4aea9498] {\n    height: 600px;\n    width: 62.5%;\n    float: left;\n}\n.login-body .login-form[data-v-4aea9498] {\n    width: 356px;\n    float: left;\n    padding: 0 0 30px 44px;\n    background-color: #ffeceb;\n    margin-top: 80px;\n    -webkit-opacity: .9;\n    -moz-opacity: .9;\n    -khtml-opacity: .9;\n    opacity: .9;\n    filter: alpha(opacity=90);\n    /*ie兼容透明度*/\n    /*登录框框头tab*/\n    /*登录错误提示*/\n    /*登录框表单*/\n}\n.login-body .login-form .login-tab[data-v-4aea9498] {\n      height: 70px;\n      margin-left: -38px;\n}\n.login-body .login-form .login-tab .mod[data-v-4aea9498] {\n        width: 50%;\n        border-bottom: 1px solid #ccc;\n}\n.login-body .login-form .login-tab .left_mod[data-v-4aea9498] {\n        display: block;\n        width: 119px;\n        margin: 10px auto 0;\n}\n.login-body .login-form .login-tab a[data-v-4aea9498] {\n        height: 43px;\n        line-height: 42px;\n        text-align: center;\n        font-size: 15px;\n        font-weight: 700;\n        color: #3c3c3c;\n}\n.login-body .login-form .login-tab .right_mod[data-v-4aea9498] {\n        display: block;\n        width: 119px;\n        margin: 10px 30px 0;\n}\n.login-body .login-form .login-tab .tab-on[data-v-4aea9498] {\n        color: #fe617a;\n        height: 41px;\n        border-bottom: 2px solid #ff4066;\n}\n.login-body .login-form .error_tip[data-v-4aea9498] {\n      background: url(https://s10.mogucdn.com/pic/140408/o613k_kqzfunswozbg2s2ugfjeg5sckzsew_16x16.png) 12px no-repeat #fffff8;\n      border: 1px solid #ffd797;\n      height: 30px;\n      line-height: 30px;\n      color: #ff1877;\n      width: 273px;\n      padding-left: 40px;\n      margin-bottom: 9px;\n      font-size: 12px;\n}\n.login-body .login-form .sign-form[data-v-4aea9498] {\n      /*输入框*/\n      /*按钮*/\n}\n.login-body .login-form .sign-form .lg_item[data-v-4aea9498] {\n        margin-bottom: 15px;\n}\n.login-body .login-form .sign-form .pwd_text[data-v-4aea9498] {\n        height: 40px;\n        width: 300px;\n        background: none !important;\n        border: 1px solid #ccc;\n        padding: 0 6px;\n}\n.login-body .login-form .sign-form .login-button[data-v-4aea9498] {\n        padding-top: 10px;\n}\n.login-body .login-form .sign-form .login-button .sub[data-v-4aea9498] {\n          display: inline;\n          float: left;\n          border: none;\n          width: 314px;\n          height: 40px;\n          background: #ff5777;\n          border-radius: 2px;\n          cursor: pointer;\n          color: #fff;\n}\n\n/*页脚*/\n.login-footer[data-v-4aea9498] {\n  padding-top: 30px;\n  background-color: #f5f5f5;\n  border-top: 1px solid #ddd;\n  font-size: 12px;\n  color: #666;\n}\n.login-footer .copyright[data-v-4aea9498] {\n    line-height: 22px;\n    padding: 12px 192px;\n    text-align: center;\n}\n',""])},558:function(n,t,a){var o=a(552);"string"==typeof o&&(o=[[n.i,o,""]]),o.locals&&(n.exports=o.locals);a(533)("3a50fe34",o,!0)},566:function(n,t){n.exports={render:function(){var n=this,t=n.$createElement,a=n._self._c||t;return a("div",[n._m(0),n._v(" "),a("div",{staticClass:"login-body",staticStyle:{background:"url(http://s17.mogucdn.com/p2/170105/upload_541i9di2b3icf9j13f24e0bg7b1i6_1920x600.png) no-repeat center center"}},[a("div",{staticClass:"login-banner"}),n._v(" "),a("div",{staticClass:"login-form"},[n._m(1),n._v(" "),a("div",{staticClass:"form-box",attrs:{id:"signIn"}},[a("p",{directives:[{name:"show",rawName:"v-show",value:n.loginErrorMsg,expression:"loginErrorMsg"}],staticClass:"error_tip",staticStyle:{display:"block"}},[n._v(n._s(n.loginErrorMsg))]),n._v(" "),a("div",{staticClass:"sign-form"},[a("div",{staticClass:"mod-box"},[a("div",{staticClass:"lg_item"},[a("input",{directives:[{name:"model",rawName:"v-model",value:n.mobile,expression:"mobile"}],staticClass:"pwd_text",staticStyle:{"border-color":"#CFCFCF"},attrs:{type:"text",maxlength:"32","data-type":"username",name:"uname",placeholder:"用户名/邮箱/手机号"},domProps:{value:n.mobile},on:{input:function(t){t.target.composing||(n.mobile=t.target.value)}}})]),n._v(" "),a("div",{staticClass:"ui-sign-item ui-sign-common-item lg_item lg_pass"},[a("input",{directives:[{name:"model",rawName:"v-model",value:n.password,expression:"password"}],staticClass:"pwd_text",staticStyle:{"border-color":"#CFCFCF"},attrs:{type:"password",maxlength:"32","data-type":"loginpassword",name:"pass",value:"",placeholder:"密码"},domProps:{value:n.password},on:{input:function(t){t.target.composing||(n.password=t.target.value)}}})]),n._v(" "),a("div",{staticClass:"l-captcha",attrs:{"data-site-key":"9e750405823bec3b550aaf752571397d"}})]),n._v(" "),a("div",{staticClass:"login-button"},[a("input",{staticClass:"sub",attrs:{type:"submit",value:"登录"},on:{click:n.doLogin}})])])])])]),n._v(" "),n._m(2),n._v(" "),a("remote-js",{attrs:{src:"//captcha.luosimao.com/static/dist/api.js"}})],1)},staticRenderFns:[function(){var n=this,t=n.$createElement,a=n._self._c||t;return a("div",{staticClass:"login-header"},[a("div",{staticClass:"logo"},[a("a",{staticClass:"logo_img",attrs:{title:"轻松瘦首页",href:"#","data-ptp-cache-id":"1.z1Oeeb.0.0.FsG0SaY"}})])])},function(){var n=this,t=n.$createElement,a=n._self._c||t;return a("div",{staticClass:"login-tab"},[a("div",{staticClass:"fl mod"},[a("a",{staticClass:"left_mod tab-on",attrs:{href:"javascript:;",title:"普通登入"}},[n._v("普通登录")])]),n._v(" "),a("div",{staticClass:"fl mod"},[a("a",{staticClass:"right_mod",attrs:{href:"javascript:;",title:"手机免密码登入"}},[n._v("手机无密码登录")])])])},function(){var n=this,t=n.$createElement,a=n._self._c||t;return a("div",{staticClass:"login-footer"},[a("div",{staticClass:"copyright"},[n._v("Copyright ©2018 lomoye.top  \n            "),a("a",{attrs:{href:"#",target:"_blank"}},[n._v("电信与信息服务业务经营许可证20110123号")]),n._v("\n             \n            "),a("a",{attrs:{href:"#",target:"_blank"}},[n._v("经营性网站备案信息")]),n._v("\n             "),a("br"),n._v("\n            浙ICP备20110123号  浙公网安备20110123   客服电话：4000-800-110  文明办网文明上网举报电话：010-82615762  \n            "),a("a",{attrs:{href:"http://net.china.com.cn/index.htm",target:"_blank"}},[n._v("违法不良信息举报中心")])])])}]}}});