<template>
	<div class='Login-contener'>
		<el-card class="box_card">
			<div>
				<h2 style="width: 100%;">台球管理系统</h2>
			</div>
			<el-form>
				<el-form-item>
					<!-- v-model 给输入框绑定变量 双向绑定  一般用在输入框上-->
					<el-input placeholder="请输入用户名" prefix-icon="el-icon-user" v-model="admname"></el-input>
				</el-form-item>
			</el-form>
			<el-form>
				<el-form-item>
					<el-input placeholder="请输入密码" show-password prefix-icon="el-icon-lock" v-model="admpassword"></el-input>
				</el-form-item>
				<el-form-item>
					<el-input v-model="vcode" placeholder="请输入验证码" style="width: 200px;"></el-input>
					<img :src="vImgCode" @click="getVcode()" />
				</el-form-item>
			</el-form>
			<el-form>
				<el-form-item>
					<!-- v-on:触发事件    click事件名称="触发事件处理方法"  v-on:简写：@-->
					<el-button type=primary style="width: 100%;" icon=el-icon-user-solid @click="login">登录</el-button>
				</el-form-item>
			</el-form>
		</el-card>
	</div>
</template>

<script>
	/* 导出vue中的对象 */
	export default{
		data(){
			return{
				admname:'',
				admpassword:'',
				vImgCode:'',
				vcode:''
			}
		},
		methods:{
			login(){
				//获取用户输入的用户名和密码
				//定义一个空对象，params是弱类型对象，事先不定义属性，当要使用的时候在定义。
				var params = {}
				params.admname=this.admname;
				params.admpassword=this.admpassword;
				//封装图片验证码
				params.vcode = this.vcode;
				
				console.log(params);
				
				//将用户名和密码发送给后台服务验证，然后接受后台服务返回的结果，来判断用户是否登陆成功.
				this.$axios.post('/adminTable/login',params)
				.then(result =>{
					console.log(result);
					var data=result.data;
					//如果登陆成功则跳转页面，否则提示登陆失败。
					if(data.code == 200){
						//把后台传回的adminname保存到缓存中
						sessionStorage.setItem("admname",data.data);
						//存token
						/* sessionStorage.setItem("token",data.data.token); */
						
						//页面跳转
						this.$message.success(data.msg);
						this.$router.push("/sys");
					}else{
						this.$message.warning(data.msg);
					}
				})
				.catch(e=>{
					console.log(e);
				})
				
			},
			getVcode(){
				this.$axios.get('/adminTable/vcode')
				.then(result=>{
					var data = result.data;
					this.vImgCode = data.data;
				})
				.catch(e=>{
					console.log(e);
				})
			}
		},
		mounted(){
			this.getVcode();
		}
	}
</script>

<!-- scoped表示style中的样式只在当前文件中生效 -->
<style scoped>
	/* 小数点--类选择器（class） */
 .box_card{
	 width: 25vw;
	 color: #000000;
	 text-align: center;
 }
 
 .Login-contener{
	 display: flex;  /* 弹性布局*/
	 justify-content: center; /* card居中*/
	 align-items: center;  /* card里面的元素也居中*/
	 /* vh:浏览器高度的百分之一,vw:浏览器宽度的百分之一 */
	 height: 100vh;
	 background-image: url(../../assets/login-background2.jpg);
 }
  img{
	  line-height: 40px;
	  vertical-align: bottom;
  }

 
</style>
