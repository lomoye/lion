webpackJsonp([2],{535:function(t,e,i){i(561);var n=i(213)(i(546),i(570),"data-v-dbda43be",null);t.exports=n.exports},540:function(t,e,i){t.exports={default:i(541),__esModule:!0}},541:function(t,e,i){i(143),i(142),t.exports=i(542)},542:function(t,e,i){var n=i(37),a=i(214);t.exports=i(33).getIterator=function(t){var e=a(t);if("function"!=typeof e)throw TypeError(t+" is not iterable!");return n(e.call(t))}},546:function(t,e,i){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var n=i(540),a=i.n(n);e.default={data:function(){return{form:{startTime:new Date,endTime:new Date((new Date).getTime()+6048e5),targetWeight:""},sportItemList:[],inputVisible:!1,inputValue:"",rules:{startTime:[{required:!0,message:"请输入开始时间"}],endTime:[{required:!0,message:"请输入结束时间"}],targetWeight:[{required:!0,message:"请输入目标体重"}]}}},created:function(){this.listSportItem()},methods:{showInput:function(){var t=this;this.inputVisible=!0,this.$nextTick(function(e){t.$refs.saveTagInput.$refs.input.focus()})},handleInputConfirm:function(){var t=!0,e=!1,i=void 0;try{for(var n,r=a()(this.sportItemList);!(t=(n=r.next()).done);t=!0){var s=n.value;if(s.name===this.inputValue)return s.checked=!0,this.inputVisible=!1,void(this.inputValue="")}}catch(t){e=!0,i=t}finally{try{!t&&r.return&&r.return()}finally{if(e)throw i}}this.inputValue&&this.sportItemList.push({name:this.inputValue,checked:!0}),this.inputVisible=!1,this.inputValue=""},quickChooseTime:function(t){this.form.endTime=new Date(this.form.startTime.getTime()+1e3*t*3600*24)},onSubmit:function(t){var e=this;this.$refs[t].validate(function(t){if(!t)return!1;e.addSportPlan()})},addSportPlan:function(){var t={};t.startTime=this.form.startTime,t.endTime=this.form.endTime,t.targetWeight=100*this.form.targetWeight,t.sportItemList=[];var e=!0,i=!1,n=void 0;try{for(var r,s=a()(this.sportItemList);!(e=(r=s.next()).done);e=!0){var o=r.value;o.checked&&t.sportItemList.push(o)}}catch(t){i=!0,n=t}finally{try{!e&&s.return&&s.return()}finally{if(i)throw n}}this.$axios.post("/api/sportPlan",t).then(function(t){this.$message.success("创建运动计划成功"),this.$router.back()}.bind(this))},listSportItem:function(){this.$axios.post("/api/sportItem/list").then(function(t){this.sportItemList=t.data.data}.bind(this))}}}},555:function(t,e,i){e=t.exports=i(532)(!1),e.push([t.i,"\n.el-tag[data-v-dbda43be] {\n  cursor: pointer;\n}\n.button-tag[data-v-dbda43be] {\n  height: 32px;\n  line-height: 30px;\n  padding-top: 0;\n  padding-bottom: 0;\n}\n.input-new-tag[data-v-dbda43be] {\n  width: 90px;\n  vertical-align: bottom;\n}\n",""])},561:function(t,e,i){var n=i(555);"string"==typeof n&&(n=[[t.i,n,""]]),n.locals&&(t.exports=n.locals);i(533)("42fdae61",n,!0)},570:function(t,e){t.exports={render:function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",[i("el-form",{ref:"form",attrs:{model:t.form,rules:t.rules,"label-width":"80px"}},[i("el-form-item",{attrs:{label:"计划时间",prop:"startTime"}},[i("el-col",{attrs:{span:8}},[i("el-date-picker",{staticStyle:{width:"100%"},attrs:{editable:!1,type:"date"},model:{value:t.form.startTime,callback:function(e){t.$set(t.form,"startTime",e)},expression:"form.startTime"}})],1)],1),t._v(" "),i("el-form-item",{attrs:{prop:"endTime"}},[i("el-col",{attrs:{span:8}},[i("el-date-picker",{staticStyle:{width:"100%"},attrs:{editable:!1,type:"date"},model:{value:t.form.endTime,callback:function(e){t.$set(t.form,"endTime",e)},expression:"form.endTime"}})],1)],1),t._v(" "),i("el-form-item",[i("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:"时间快捷选择:7天",placement:"top-start"}},[i("el-tag",{attrs:{color:"#ccc"},nativeOn:{click:function(e){t.quickChooseTime(7)}}},[t._v("7天")])],1),t._v(" "),i("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:"时间快捷选择:15天",placement:"top-start"}},[i("el-tag",{attrs:{color:"#ccc"},nativeOn:{click:function(e){t.quickChooseTime(15)}}},[t._v("15天")])],1),t._v(" "),i("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:"时间快捷选择:30天",placement:"top-start"}},[i("el-tag",{attrs:{color:"#ccc"},nativeOn:{click:function(e){t.quickChooseTime(30)}}},[t._v("30天")])],1)],1),t._v(" "),i("el-form-item",{attrs:{label:"目标体重",prop:"targetWeight"}},[i("el-col",{attrs:{span:8}},[i("el-input",{attrs:{type:"number",placeholder:"公斤"},model:{value:t.form.targetWeight,callback:function(e){t.$set(t.form,"targetWeight",e)},expression:"form.targetWeight"}})],1)],1),t._v(" "),i("el-form-item",{attrs:{label:"运动项目"}},[t._l(t.sportItemList,function(e,n){return i("el-checkbox",{key:n,model:{value:e.checked,callback:function(i){t.$set(e,"checked",i)},expression:"item.checked"}},[t._v("\n                "+t._s(e.name)+"\n            ")])}),t._v(" "),t.inputVisible?i("el-input",{ref:"saveTagInput",staticClass:"input-new-tag",attrs:{size:"small"},on:{blur:t.handleInputConfirm},nativeOn:{keyup:function(e){if(!("button"in e)&&t._k(e.keyCode,"enter",13,e.key))return null;t.handleInputConfirm(e)}},model:{value:t.inputValue,callback:function(e){t.inputValue=e},expression:"inputValue"}}):i("el-button",{staticClass:"button-tag",attrs:{size:"small"},on:{click:t.showInput}},[t._v("+ 新运动项目")])],2),t._v(" "),i("el-form-item",[i("el-button",{attrs:{type:"primary"},on:{click:function(e){t.onSubmit("form")}}},[t._v("确定")])],1)],1)],1)},staticRenderFns:[]}}});