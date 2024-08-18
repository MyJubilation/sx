<template>
	<div class="index-container">
		<el-container>
		  <el-aside width="200px">
			  <el-row class="tac">
			    <!-- <el-col :span="12"> -->
			      <h3>菜单</h3>
			      <el-menu
			       default-active="2"
			        class="el-menu-vertical-demo"
					router>

					<!-- v-bind表示将变量绑定到标签属性上，简写为":" -->
			        <el-submenu index="1">
			          <template slot="title">
			            <i class="el-icon-coordinate"></i>
			            <span>台球桌管理</span>
			          </template>
					  <el-menu-item-group>
					            <el-menu-item index="1-1"  @click="billiardsMenuItemClick">台球桌信息</el-menu-item>
					          </el-menu-item-group>
			        </el-submenu>
					<el-submenu index="2">
					  <template slot="title">
					    <i class="el-icon-user-solid"></i>
					    <span>用户管理</span>
					  </template>
					  <el-menu-item-group>
					            <el-menu-item index="2-1"  @click="handleMenuItemClick">用户信息</el-menu-item>
					          </el-menu-item-group>
					</el-submenu>
					<el-submenu index="3">
					  <template slot="title">
					    <i class="el-icon-burger"></i>
					    <span>食品管理</span>
					  </template>
					  <el-menu-item-group>
					            <el-menu-item index="3-1" @click="foodMenuItemClick">食品购买信息</el-menu-item>
					          </el-menu-item-group>
					</el-submenu>


			      </el-menu>
			  </el-row>
		  </el-aside>
		  <el-container>
			 <el-header>
				 <div>
			      <span>台球管理系统</span>
				  </div>

				  <div>
					 <el-menu default-active="1" class="el-menu-demo" mode="horizontal" background-color="#d1d1cc">
					   <el-submenu index="1">
					     <template slot="title">{{admname}}</template>
					     <el-menu-item index="1-1" @click="logout">退出登录</el-menu-item>
					     </el-submenu>
					 </el-menu>
				  </div>

			    </el-header>

		    <el-main>
				<router-view />
			</el-main>
		  </el-container>
		</el-container>
	</div>
</template>

<script>


	  export default{
		  data(){
			  return{
				  menuList:[],
				  admname:sessionStorage.getItem("admname"),
			  }
		  },
		  methods:{
        billiardsMenuItemClick(){
          this.$router.push('/sys/billiards');
        },
         handleMenuItemClick() {
              // 跳转到 UserList 页面
              this.$router.push('/sys/UserList');
            },
            foodMenuItemClick(){
              this.$router.push('/sys/foodlist');
            },
			  logout(){
				  //1.清除缓存
				  sessionStorage.clear();

				  //2.跳转到登录页面
				  this.$router.push('/');
			  }
		  },
		  mounted(){
			  //mounted表示在页面渲染完成后自动触发的事件方法

		  }
	  }

</script>

<style scoped>


	.el-header {
	    background-color: #d1d1cc;
	    color: #333;
	    text-align: center;
	    line-height: 60px;
		display: flex;
		justify-content: space-between;
		align-items: center;
	  }

	  .el-aside {
	    background-color: #ffffff;
	    color: #333;
	    text-align: center;
	    line-height: 50px;
		overflow: hidden;
	  }


	  body > .el-container {
	    height: 100vh;
	  }

	  .el-container:nth-child(5) .el-aside,
	  .el-container:nth-child(6) .el-aside {
	    line-height: 260px;
	  }

	  .el-container:nth-child(7) .el-aside {
	    line-height: 320px;
	  }

	  .el-container{
		 height: 100vh;
	  }



</style>
