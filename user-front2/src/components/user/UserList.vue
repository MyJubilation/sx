<template>
	<div>
		<el-form :inline="true">
			<el-form-item>
				<el-input v-model="userName" placeholder="用户名"></el-input>
			</el-form-item>
			<el-form-item>
				<el-button type="primary" @click="getUserList">查询</el-button>
			</el-form-item>
      <el-form-item>
        <el-button type="primary" @click="showAddUserDialog">添加用户</el-button>
      </el-form-item>
		</el-form>

        <el-table
        		    :data="userList"
        		    border
        		    style="width: 100%"
                max-height="750">
        		    <el-table-column
        		      fixed
        		      prop="userId"
        		      label="序号"
        		      >
        		    </el-table-column>
        		    <el-table-column
        		      prop="userName"
        		      label="用户名"
        		      >
        		    </el-table-column>
        		    <el-table-column
        		      prop="remainMoney"
        		      label="剩余金额"
        		      >
        		    </el-table-column>
        		    <el-table-column
        		      prop="userStatus"
        		      label="状态"
        		      >
        		    </el-table-column>
        		    <el-table-column
        		      fixed="right"
        		      label="操作"
        		      >
        		      <template slot-scope="scope">
        		        <el-button type="text" size="small" @click="showUpdateUserDialog(scope.row)" >修改</el-button>
        		        <el-button type="text" size="small" @click="deleteUser(scope.row.userId)" >删除</el-button>
        		      </template>
        		    </el-table-column>
        		  </el-table>

        		  <!-- 分页组件-->
        		  <!--
        		  @size-change:当每页显示条数发生变化是发生的事件
        		  @currentchange:当页码发生变化是触发的事件
        		  page-sizes:每页显示条数集合 total:数据条数
        		  -->
        		  <el-pagination
        		        @size-change="handleSizeChange"
        		        @current-change="handleCurrentChange"
        		        :current-page="pageNumber"
        		        :page-sizes="[5,10,15,20]"
        		        :page-size="pageSize"
        		        layout="total, sizes, prev, pager, next"
        		        :total="total">
        		      </el-pagination>

                  <el-dialog title="添加用户" :visible.sync="addUserFormVisible">
                    <el-form :model="addUser">
                      <el-form-item label="用户名" label-width="120px">
                        <el-input v-model="addUser.userName" autocomplete="off"></el-input>
                      </el-form-item>
                      <el-form-item label="初始密码" label-width="120px">
                        <el-input v-model="addUser.userPassword" autocomplete="off" show-password></el-input>
                      </el-form-item>
                      <el-form-item label="剩余金额" label-width="120px">
                        <el-input v-model="addUser.remainMoney" auto-complete="off"></el-input>
                      </el-form-item>
                      <el-form-item label="状态" label-width="120px">
                        <el-input v-model="addUser.userStatus" auto-complete="off"></el-input>
                      </el-form-item>

                    </el-form>
                    <div slot="footer" class="dialog-footer">
                      <el-button @click="cancelAddUser">取 消</el-button>
                      <el-button type="primary" @click="saveUser">确 定</el-button>
                    </div>
                  </el-dialog>
        			  <!-- 修改用户-->
        			  <el-dialog title="修改用户" :visible.sync="updateUserFormVisible">
        			    <el-form :model="updateUser">
        					<el-form-item label="ID" label-width="120px">
        					  <el-input v-model="updateUser.userId" autocomplete="off" :disabled="true"></el-input>
        					</el-form-item>
        			      <el-form-item label="用户名" label-width="120px">
        			        <el-input v-model="updateUser.userName" autocomplete="off"></el-input>
        			      </el-form-item>
        			      <el-form-item label="初始密码" label-width="120px">
        			        <el-input v-model="updateUser.userPassword" autocomplete="off" show-password></el-input>
        			      </el-form-item>

        			      <el-form-item label="剩余金额" label-width="120px">
        			        <el-input v-model="updateUser.remainMoney" auto-complete="off"></el-input>
        			      </el-form-item>
        			      <el-form-item label="状态" label-width="120px">
        			        <el-input v-model="updateUser.userStatus" auto-complete="off"></el-input>
        			      </el-form-item>
        			    </el-form>
        			    <div slot="footer" class="dialog-footer">
        			      <el-button @click="cancelUpdateUser">取 消</el-button>
        			      <el-button type="primary" @click="modifyUser">确 定</el-button>
        			    </div>
        			  </el-dialog>
			</div>
</template>

<script>
	export default{
		data(){
			return{
				userList:[],
				pageNumber:1,
				pageSize:5,
				total:0,
				userName:'',
				updateUserFormVisible:false,
				updateUser:{},
        addUserFormVisible:false,
        addUser:{}
			}
		},
		methods:{
      cancelUpdateUser(){
        this.updateUserFormVisible=false;
      },
      showAddUserDialog(){
        
        this.addUserFormVisible = true
      },
      cancelAddUser(){
      this.addUser={};
      this.addUserFormVisible=false;
      },
      saveUser(){
      this.$axios.post('/userTable/add',this.addUser)

      .then(result => {
        let data = result.data;
        if(data.code == 200){
          this.$message.success(data.msg);
          this.addUserFormVisible=false;
          this.getUserList();
        }else{
          this.$message.warning(data.msg);

        }

      })

      .catch(e => {
        console.log(e);
      })
      },
      handleSizeChange(val){
        // 将修改的pageSize值赋给this.pageSize
        this.pageSize = val;
        // 重新调用getUserList方法，按照新的pageSize查询
        this.getUserList();
      },
      handleCurrentChange(val){
        this.pageNumber = val;
        this.getUserList();
      },
      deleteUser(userId){
        console.log(userId);
        this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
                  confirmButtonText: '确定',
                  cancelButtonText: '取消',
                  type: 'warning'
                }).then(() => {
                  this.$axios.get('/userTable/delete',{params:{
                    "userId":userId,
                    },
                    })
                    .then(result =>{
                      let data = result.data;
                      if(data.code == 200){
                        this.$message.success(data.msg);
                        this.getUserList();
                      }else{
                        this.$message.error(data.msg);
                      }
                    })
                    .catch(e =>{
                      console.log(e);
                    })
                }).catch(() => {
                  this.$message({
                    type: 'info',
                    message: '已取消删除'
                  });
                });

      },
      showUpdateUserDialog(row){
      this.updateUserFormVisible = true;
        this.updateUser = row;
      },
      modifyUser(){
        this.$axios.post('/userTable/update',this.updateUser)
        .then(result =>{
          let data = result.data;
          if(data.code == 200){
            this.$message.success(data.msg);
            this.updateUserFormVisible=false;
          }else{
            this.$message.warning(data.msg);
          }
        })
        .catch(e =>{
          console.log(e);
        })
      },
			getUserList(){
			  let params = {};
			  params.pageNumber = this.pageNumber;
			  params.pageSize = this.pageSize;

			  if(this.userName != ''){
			    params.userName = this.userName;
			  }

			  this.$axios.get('/userTable/page',{params:params})
			  .then(result=>{
			    let data = result.data;
			    this.userList = data.data.rows;
			    this.total = data.data.total;

			  })
			  .catch(e=>{
			    console.log(e);
			  })
			},
  },
  mounted(){
  	this.getUserList();
  }
}
</script>

<style scoped>
	 .avatar-uploader .el-upload {
	    border: 1px dashed #d9d9d9;
	    border-radius: 6px;
	    cursor: pointer;
	    position: relative;
	    overflow: hidden;
	  }
	  .avatar-uploader .el-upload:hover {
	    border-color: #409EFF;
	  }
	  .avatar-uploader-icon {
	    font-size: 28px;
	    color: #8c939d;
	    width: 178px;
	    height: 178px;
	    line-height: 178px;
	    text-align: center;
	  }
	  .avatar {
	    width: 178px;
	    height: 178px;
	    display: block;
	  }
   .el-pagination{
     position: relative;

     bottom: 0;
   }

</style>
