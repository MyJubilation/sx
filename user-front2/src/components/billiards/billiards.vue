<template>
  <div>
    <el-form>
      <el-form-item>
        <el-button @click="setResidualHoursPerBilliards" type="success" style="width: 100%;">刷新</el-button>
      </el-form-item>
      <el-form-item>
        <!-- :span="6" 表示 <el-col> 将跨越 6 个栅格，即占据了半个屏幕宽度（因为 6/24 = 0.25，即 25%） -->
        <el-row :gutter="20">
          <!-- ------------------------------------------------------------- -->
          <el-col :span="span" style="margin-top: 1vw;" v-for="(billirads,index) in billiardsList" :key="index">

            <el-form-item>

              <el-col :span="span">
                <el-card style="width: 20vw; height: 20vh; margin-left: 30px;">
                  <div style="width: 100%; display: inline-block;">
                    <!-- format="DD天HH小时mm分钟"
                            format="yyyy-MM-dd HH:mm:ss"
                       -->
                    <el-statistic format="DD天HH小时mm分钟ss秒" :value="billirads.residualTime" time-indices
                      :title="billirads.id + '号台球桌使用时间还剩'">
                    </el-statistic>
                  </div>
                </el-card>
              </el-col>
            </el-form-item>
            <el-form-item>
              <el-footer style="height: auto; display: flex; justify-content: center; align-items: flex-end;">
                <el-button-group>
                  <template slot-scope="scope">
                    <!-- <el-button type="primary" style="width: 10vw; height: 5vw; font-size: 20px;"
                      @click="setResidualHours(billirads.id)">刷新</el-button> -->
                    <el-button type="primary" style="width: 10vw; height: 5vw; font-size: 20px;"
                      @click="settleAccounts(billirads.id)">结算</el-button>
                    <el-button type="primary" style="width: 10vw; height: 5vw; font-size: 20px;"
                      @click="showUpdateBilliardsDialog(billirads.id)">更改</el-button>
                  </template>

                </el-button-group>
              </el-footer>
            </el-form-item>
          </el-col>
          <!-- ------------------------------------------------------------- -->


        </el-row>
      </el-form-item>

    </el-form>
    <!-- 修改对话框 -->
    <el-dialog title="修改台球桌信息" :visible.sync="updateBilliardsFromVisible">
      <el-form :model="updateBilliards">
        <el-form-item label="台球桌ID" label-width="120px">
          <el-input v-model="updateBilliards.id" autocomplete="off" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="到期时间" label-width="120px">
          <el-input v-model="updateBilliards.residualTime" auto-complete="off" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="价格(元/小时)" label-width="120px">
          <el-input v-model="updateBilliards.price" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="添加时间(小时)" label-width="120px">
          <el-input v-model="updateBilliards.usertime" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="当前VIP用户id" label-width="120px">
          <el-input v-model="updateBilliards.userId" autocomplete="off"></el-input>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancelUpdateBilliards">取 消</el-button>
        <el-button type="primary" @click="modifyBilliards(updateBilliards.id)">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        // deadline4: Date.now() + (new Date().setHours(23,30,59) - Date.now()),
        id: [],
        deadline: [],
        deadline1: Date.now(),
        deadline2: Date.now(),
        deadline3: Date.now(),
        deadline4: Date.now(),
        deadline5: Date.now(),
        deadline6: Date.now(),
        stop: true,
        span: 8,
        billiradsId: '',
        date: '',
        updateBilliardsFromVisible: false,
        updateBilliards: {},
        billiardsList: []
      };
    },
    methods: {
      setResidualHours(id) {
        this.$axios.get('/billiards/date', {
            params: {
              "id": id
            }
          })
          .then(result => {
            var data = result.data;
            this.date = data.data;

            // this.date = "2024-08-26T06:59:22.000+00:00";
            // this.date = "DD-HH-MM";
            // this.date = "26-06-59";

            // console.log(this.date);
            // console.log(id);
            if (id == 1) this.deadline1 = new Date(this.date);
            else if (id == 2) this.deadline2 = new Date(this.date);
            else if (id == 3) this.deadline3 = new Date(this.date);
            else if (id == 4) this.deadline4 = new Date(this.date);
            else if (id == 5) this.deadline5 = new Date(this.date);
            else if (id == 6) this.deadline6 = new Date(this.date);
          })
          .catch(e => {
            console.log(e);
          })

        // this.deadline4 = Date.now() + (new Date().setHours(this.date) - Date.now());
        // this.deadline4 = Date.now() + (new Date().setHours(23,30,59) - Date.now());
        // this.deadline4 = new Date(this.date);
      },
      setResidualHoursPerBilliards() {

        this.setResidualHours(1);
        this.setResidualHours(2);
        this.setResidualHours(3);
        this.setResidualHours(4);
        this.setResidualHours(5);
        this.setResidualHours(6);
      },
      showUpdateBilliardsDialog(id) {

        // 将当前行数据回显至对话框中
        this.$axios.get('/billiards/selectBilliardsById', {
            params: {
              "id": id
            }
          })
          .then(result => {
            var data = result.data;
            this.updateBilliards = data;
            console.log(data);
            console.log(this.updateBilliards);

            this.updateBilliardsFromVisible = true;
          })
          .catch(e => {
            console.log(e);
          })
      },
      cancelUpdateBilliards() {
        this.updateBilliardsFromVisible = false;
      },
      modifyBilliards() {
        this.$axios.post('/billiards/updateBilliards', this.updateBilliards)
          .then(result => {
            var data = result.data;
            if (data.code == 200) {
              this.$message.success(data.msg);
              this.reload();
              setTimeout(() => {
                this.cancelUpdateBilliards();
              }, 200); // 1000毫秒等于一秒
            } else {
              this.$message.warning(data.msg);
            }
          })
          .catch(e => {
            console.log(e);
          })
      },
      reload() {
        setTimeout(() => {
          this.$router.push('/sys/billiards');
          console.log(1);
        }, 0); // 1000毫秒等于一秒
        this.$router.push('/sys');
        console.log(2);
      },
      getBilliardsId() {
        this.$axios.get('/billiards/getBilliardsId')
          .then(result => {
            var data = result.data;

            // this.billiradsList = data.data;
            for (let i = 0; i < data.data.length; i++) {
              let obj = {};
              obj.id = data.data[i].id;
              obj.residualTime = new Date(data.data[i].residualTime);
              obj.userId = data.data[i].userId;
              this.billiardsList.push(obj);
            }
            console.log(billiardsList);
          })
          .catch(e => {
            console.log(e);
          })
      },
      settleAccounts(id) {
        // 弹窗二次确认结算
        // 结算，时间清零，变为当前时间，用户金额更改
        // console.log(id);
        // console.log(this.billiardsList[id-1]);
        // let billiardsData = {};
        // billiardsData.id = this.billiardsList[id - 1].id;
        // billiardsData.userId = this.billiardsList[id - 1].userId;
        // billiardsData.residualTime = this.billiardsList[id - 1].residualTime;
        this.$confirm('您将结算该台球桌, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$axios.get('/billiards/settleAccounts', {params: {
              "id": id
            }})
            .then(result => {
              var data = result.data;
              if (data.code == 200) {
                this.$message.success(data.msg);
                this.reload();
              } else {
                this.$message.warning(data.msg);
              }
            })
            .catch(e => {
              console.log(e);
            })
        }).catch((e) => {
          console.log(e);
          this.$message({
            type: 'info',
            message: '已取消结算'
          });
        });
      }

    },
    mounted() {
      this.setResidualHours(1);
      this.setResidualHours(2);
      this.setResidualHours(3);
      this.setResidualHours(4);
      this.setResidualHours(5);
      this.setResidualHours(6);
      this.getBilliardsId();
    }
  };
</script>

<style>

</style>
