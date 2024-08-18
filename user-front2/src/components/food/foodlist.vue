<template>
    <div>
        <el-form :inline="true">
            <el-form-item>
                <el-input v-model="foodName" placeholder="商品名"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="getFoodList">搜索</el-button>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="showAddFoodDialog">添加商品</el-button>
            </el-form-item>
        </el-form>
        <el-table :data="foodList" border style="width: 100%">
            <el-table-column fixed prop="id" label="序号" >
            </el-table-column>
            <el-table-column prop="imgUrl" label="图片" >
                <template slot-scope="scope">
                    <!-- todo：size -->
                    <img :src="scope.row.imgUrl" width="60px" height="40"/>
                </template>
            </el-table-column>
            <el-table-column prop="foodName" label="商品名" >
            </el-table-column>
            <el-table-column prop="foodPrice" label="单价" >
            </el-table-column>
            <el-table-column prop="foodAccount" label="库存" >
            </el-table-column>
            <el-table-column fixed="right" label="操作" >
                <template slot-scope="scope">
                <el-button type="primary" size="small" icon="el-icon-edit" circle @click="showUpdateFoodDialog(scope.row)"></el-button>
                <el-button type="danger" size="small" icon="el-icon-delete" circle @click="deleteFood(scope.row.id)"></el-button>
                </template>
            </el-table-column>
    </el-table>

        <!-- 分页组件 -->
        <!--
        @size-change:当每页显示条数发生变化时除法的事件
        @current-change:当页码发生变化时触发的事件 -->
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="pageNumber"
        :page-sizes="[5, 10, 20, 30, 40]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper"
        :total="total">
        </el-pagination>

        <!-- 添加商品对话框 -->
         <!--
        visible.sync 控制对话框是否显示的标记， 为true显示，false不显示 -->
        <el-dialog title="添加商品" :visible.sync="addFoodFormVisible">
            <el-form :model="addFood">
                <el-form-item label="商品名" label-width="120px">
                    <el-input v-model="addFood.foodName"></el-input>
                </el-form-item>
                <el-form-item label="库存" label-width="120px">
                    <el-input-number v-model="addFood.foodAccount" :min="0" :max="99999" label="添加库存"></el-input-number>
                </el-form-item>
                <el-form-item label="单价" label-width="120px">
                    <el-input-number v-model="addFood.foodPrice" :precision="2" :step="0.1" :max="99999"></el-input-number>
                </el-form-item>
                <el-form-item label="图片" label-width="120px">
                    <!-- 头像上传组件

                    acction:向后台服务发送请求，注意：请求路径要带上后台服务的域名 -->
                    <el-upload
                    class="avatar-uploader"
                    action="http://localhost:8084/food/img"
                    :show-file0list="false"
                    :on-success="handleAvataSuccess">
                        <img v-if="imgUrl" :src="imgUrl" class="avatar">
                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                    </el-upload>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button round type="danger" @click="cancelAddFood">取 消</el-button>
                <el-button round type="success" @click="saveFood" >确 定</el-button>
            </div>
        </el-dialog>

        <!-- 修改商品对话框 -->
        <el-dialog title="修改商品" :visible.sync="updateFoodFormVisible">
            <el-form :model="updateFood">
                <el-form-item label="商品名" label-width="120px">
                    <el-input v-model="updateFood.foodName"></el-input>
                </el-form-item>
                <el-form-item label="库存" label-width="120px">
                    <el-input-number v-model="updateFood.foodAccount" :min="0" :max="99999" label="添加库存"></el-input-number>
                </el-form-item>
                <el-form-item label="单价" label-width="120px">
                    <el-input-number v-model="updateFood.foodPrice" :precision="2" :step="0.1" :max="99999"></el-input-number>
                </el-form-item>
                <el-form-item label="头像" label-width="120px">
                    <!-- 头像上传组件

                    acction:向后台服务发送请求，注意：请求路径要带上后台服务的域名 -->
                    <el-upload
                    class="avatar-uploader"
                    action="http://localhost:8084/food/img"
                    :show-file0list="false"
                    :on-success="handleUpdateAvataSuccess">
                        <img v-if="imgUrl" :src="imgUrl" class="avatar">
                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                    </el-upload>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button round type="danger" @click="cancelUpdateFood">取 消</el-button>
                <el-button round type="success" @click="modifyFood" >确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    export default{
        data(){
            return{
                foodList:[],
                pageNumber:1,
                pageSize:5,
                total:0,
                foodName:'',
                addFoodFormVisible:false,
                addFood:{},
                imgUrl:'',
                updateFoodFormVisible:false,
                updateFood:{}
            }
        },
        methods:{
            getFoodList(){
                var params= {};
                params.pageNumber = this.pageNumber;
                params.pageSize = this.pageSize;

                if(this.foodName != ''){
                    params.foodName = this.foodName;
                    console.log(params.foodName);
                }

                this.$axios.get('/food/page', {params : params})
                .then(result => {
                    var data = result.data;
                    this.foodList = data.data.rows;
                    this.total = data.data.total;
                })
                .catch(e => {
                    console.log(e);
                })
            },
            handleSizeChange(val){
                //将修改后的pageSize值赋给this.pageSize
                this.pageSize = val;

                //重新调用getFoodList方法，按照新的pageSize重新查询
                this.getFoodList();
            },
            handleCurrentChange(val){
                this.pageNumber = val;
                this.getFoodList();
            },
            //添加商品对话框取消按钮方法
            cancelAddFood(){
                //将addUser对象清空
                this.addFood = {};
                this.imgUrl = '';
                //将对话框标记为false
                this.addFoodFormVisible = false;
            },
            //添加商品对话框确定Food
            saveFood(){
                this.$axios.post('/food/add', this.addFood)
                .then(result => {
                    var data = result.data;
                    if(data.code == 200){
                        //提示信息
                        this.$message.success(data.msg);
                        //关闭对话框
                        this.addFoodFormVisible = false;
                        //局部刷新结果
                        this.getFoodList();
                    }else{
                        //提示信息
                        this.$message.warning(data.msg);
                    }
                })
                .catch(e => {
                    console.log(e);
                })
            },
            showAddFoodDialog(){
                //设置addUserFormVisible为true
                this.addFoodFormVisible = true;
            },
            handleAvataSuccess(res, file){
                this.imgUrl = res.data;
                this.addFood.imgUrl = this.imgUrl;
                console.log(this.addFood);
            },
            showUpdateFoodDialog(row){
                this.updateFoodFormVisible = true;

                //将当前行数据回显至对话框中
                this.updateFood = row;
                this.imgUrl = row.imgUrl;
            },
            cancelUpdateFood(){
                this.updateFoodFormVisible = false;
                //从页面修改的效果看，如果修改了字段项，点击取消后修改值依然存在，所以刷新列表重新从数据库取值
                this.getFoodList();
            },
            modifyFood(){
                this.$axios.post('food/update', this.updateFood)
                .then(result => {
                    var data = result.data;
                    if(data.code == 200){
                        this.$message.success(data.msg);
                        this.updateFoodFormVisible = false;
                    }else{
                        this.$message.warning(data.msg);
                    }
                })
                .catch(e => {
                    console.log(e);
                })
            },
            handleUpdateAvataSuccess(res, file){
                this.imgUrl = res.data;
                this.updateFood.imgUrl = this.imgUrl;
            },
            deleteFood(id){
                this.$confirm('此操作将永久删除该商品, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$axios.get('/food/delete', {
                        params: {
                            "id":id
                        }
                    })
                    .then(result => {
                        var data = result.data;
                        if(data.code == 200){
                            this.$message.success(data.msg);
                            this.getFoodList();
                        }
                    })
                    this.$message({
                        type: 'success',
                        message: '删除成功!'
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            }
        },
        mounted() {
            this.getFoodList();
        }
    }
</script>

<style>
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
</style>
