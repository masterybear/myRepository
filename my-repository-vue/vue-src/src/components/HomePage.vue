<template>
  <!-- 修改-弹框 -->
  <el-dialog v-model="dialogUpdate" title="修改信息" width="30%"
             style="--el-color-primary: #135187;--el-text-color-primary: #BABEC6">
    <el-form :model="brand" label-width="120px">
      <el-form-item label="品牌名称">
        <el-input v-model="brand.brandName"></el-input>
      </el-form-item>
      <el-form-item label="公司名称">
        <el-input v-model="brand.companyName"></el-input>
      </el-form-item>
      <el-form-item label="排序">
        <el-input v-model="brand.ordered" placeholder="须为纯数字格式"></el-input>
      </el-form-item>
      <el-form-item label="当前状态">
        <el-switch :active-value="1" :inactive-value="0" v-model="brand.state"></el-switch>
      </el-form-item>
      <el-form-item label="说明">
        <el-input type="textarea" v-model="brand.description"></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
          <span class="dialog-footer">
            <el-button @click="closeDialog">取消</el-button>
            <el-button type="primary" @click="updateBrand">立即修改</el-button>
          </span>
    </template>
  </el-dialog>
  <!-- 创建-弹框 -->
  <el-dialog title="新增信息" v-model="dialogCreate" width="30%"
    style="--el-color-primary: #135187;--el-text-color-primary: #BABEC6">
    <el-form ref="form" :model="brand" label-width="80px">
      <el-form-item label="品牌名称">
        <el-input v-model="brand.brandName"></el-input>
      </el-form-item>
      <el-form-item label="公司名称">
        <el-input v-model="brand.companyName"></el-input>
      </el-form-item>
      <el-form-item label="排序">
        <el-input v-model="brand.ordered" placeholder="须为纯数字格式"></el-input>
      </el-form-item>
      <el-form-item label="当前状态">
        <el-switch :active-value="1" :inactive-value="0" v-model="brand.state"></el-switch>
      </el-form-item>
      <el-form-item label="说明">
        <el-input type="textarea" v-model="brand.description"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="createInfo">立即创建</el-button>
        <el-button @click="closeDialog">取消</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
  <div id="outside" class="fontA">
  <div id="inside">
    <div id="topSide">
      <div class="topA">
        <h2>Repository</h2>
      </div>
      <div style="width: 100px">
        <el-dropdown>
          <el-avatar class="avatar-style" size="default" :src="circleUrl"/>
          <template #dropdown>
            <el-dropdown-menu style="width: 150px">
              <div class="fontB"><span>{{ username }}</span></div>
              <el-dropdown-item @click="changeUser"><div class="fontB">切换用户</div></el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
    </div>
    <div id="week-content">
      <div id="week-block"><strong id="week-font">MON</strong><div id="1"></div></div>
      <div id="week-block"><strong id="week-font">TUE</strong><div id="2"></div></div>
      <div id="week-block"><strong id="week-font">WED</strong><div id="3"></div></div>
      <div id="week-block"><strong id="week-font">THU</strong><div id="4"></div></div>
      <div id="week-block"><strong id="week-font">FRI</strong><div id="5"></div></div>
      <div id="week-block"><strong id="week-font">SAT</strong><div id="6"></div></div>
      <div id="week-block"><strong id="week-font">SUN</strong><div id="0"></div></div>
    </div>
    <!-- 正文区域 -->
    <Transition name="mis">
      <div class="mis-side" v-show="misFlag">
        <div class="mis-head">
          <el-form :inline="true" :model="queryBrand" class="demo-form-inline" >
            <el-form-item label="公司名称">
              <el-input v-model="queryBrand.companyName" placeholder="company name" />
            </el-form-item>
            <el-form-item label="品牌名称">
              <el-input v-model="queryBrand.brandName" placeholder="brand name" />
            </el-form-item>
            <el-form-item label="状态">
              <el-select v-model="queryBrand.state" placeholder="state" >
                <el-option  label="disable" value="0" />
                <el-option label="enable" value="1" />
              </el-select>
            </el-form-item>
            <el-form-item>
              <el-button class="active-button" type="primary" @click="checkCondition">查询</el-button>
            </el-form-item>
          </el-form>
        </div>
        <div class="mis-active">
          <div class="side-active">
            <el-button class="active-button" type="danger" @click="deleteByIds">批量删除</el-button>
            <el-button class="active-button" type="primary" @click="dialogCreate = true">新增</el-button>
          </div>
          <div>
            <el-pagination
              style="margin: 10px 0"
              v-model:current-page="currentPage"
              v-model:page-size="pageSize"
              :page-sizes="[5, 10, 20, 30, 40, 50]"
              :total="totalCount"
              layout="sizes, prev, pager, next"
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
            />
          </div>
        </div>
        <div class="mis-content">
          <el-table :data="tableData" id="mis-table" stripe @selection-change="handleSelectionChange">
            <el-table-column type="selection"></el-table-column>
            <el-table-column type="index" align="center"></el-table-column>
            <el-table-column prop="brandName" label="品牌名称" align="center"></el-table-column>
            <el-table-column prop="companyName" label="公司名称" align="center"></el-table-column>
            <el-table-column prop="ordered" label="排序" align="center"></el-table-column>
            <el-table-column prop="state" label="当前状态" align="center" :formatter="stateStr"></el-table-column>
            <el-table-column label="active" align="center">
              <template #default="scope">
                <el-button class="active-button" @click="updateClick(scope.row)" type="primary">修改</el-button>
                <el-button class="active-button" @click="deleteClick(scope.row)" type="danger">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </div>
    </Transition>
    <div id="bothSide">
      <div id="leftSide">
        <div id="left-content">
          <h3>menu</h3>
          <button class="leftSIde-button" type="button" @click="misAppear">MIS</button>
        </div>
      </div>
    </div>
  </div>
</div>
</template>
<script>
export default {
  name: 'HomePage',
  data () {
    return {
      username: '',
      circleUrl: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
      misFlag: false,
      currentPage: 1,
      pageSize: 20,
      totalCount: 20,
      flag: false,
      dialogUpdate: false,
      dialogCreate: false,
      tableData: [],
      brand: {
        id: '',
        brandName: '',
        companyName: '',
        state: '',
        ordered: '',
        description: ''
      },
      queryBrand: {
        brandName: '',
        companyName: '',
        state: ''
      },
      selectIds: [],
      multipleSelection: []
    }
  },
  methods: {
    changeUser () {
      localStorage.removeItem('token')
      this.$router.push('/sign')
    },
    handleSizeChange (val) {
      this.pageSize = val
      this.queryInfo()
    },
    handleCurrentChange (val) {
      this.currentPage = val
      this.queryInfo()
    },
    empty (delData) {
      for (const key in delData) {
        delete delData[key]
      }
    },
    misAppear () {
      this.misFlag = !this.misFlag
      this.flag === false ? this.queryInfo() : this.removeInfo()
    },
    removeInfo () {
      this.flag = !this.flag
      this.empty(this.tableData)
    },
    checkCondition () {
      this.brand = this.queryBrand
      this.queryInfo()
      this.empty(this.brand)
    },
    checkDialogInfo () {
      if (this.brand.companyName && this.brand.brandName && this.brand.description && !isNaN(this.brand.ordered)) {
        this.brand.companyName = this.brand.companyName.replace(/\s+/g, '')
        this.brand.brandName = this.brand.brandName.replace(/\s+/g, '')
        this.brand.description = this.brand.description.replace(/\s+/g, '')
        return true
      } else {
        return false
      }
    },
    createInfo () {
      if (!this.checkDialogInfo()) {
        this.closeDialog()
        this.$message({
          showClose: true,
          message: '注意格式',
          type: 'warning'
        })
      } else {
        this.axios({
          method: 'post',
          url: '/createInfo',
          data: this.$qs.stringify(this.brand) + '&' + this.$qs.stringify({ token: localStorage.getItem('token') })
        }).then(resp => {
          this.dialogCreate = false
          this.empty(this.brand)
          this.queryInfo()
          if (resp.data === 'success') {
            this.$message({
              showClose: true,
              message: '恭喜你,已成功创建信息',
              type: 'success'
            })
          } else {
            this.$message({
              showClose: true,
              message: '创建失败',
              type: 'error'
            })
          }
        }).catch(_ =>
          this.$message({
            showClose: true,
            message: '过程中出了些小故障',
            type: 'error'
          }))
      }
    },
    closeDialog () {
      this.empty(this.brand)
      this.dialogCreate = false
      this.dialogUpdate = false
    },
    updateBrand () {
      if (!this.checkDialogInfo()) {
        this.closeDialog()
        this.$message({
          showClose: true,
          message: '注意格式',
          type: 'warning'
        })
      } else {
        this.axios({
          method: 'post',
          url: '/updateInfo',
          data: this.$qs.stringify(this.brand) + '&' + this.$qs.stringify({ token: localStorage.getItem('token') })
        }).then(resp => {
          this.dialogUpdate = false
          this.empty(this.brand)
          this.queryInfo()
          if (resp.data === 'success') {
            this.$message({
              showClose: true,
              message: '恭喜你,已成功修改信息',
              type: 'success'
            })
          } else {
            this.$message({
              showClose: true,
              message: '更新失败',
              type: 'error'
            })
          }
        }).catch(_ =>
          this.$message({
            showClose: true,
            message: '过程中出了些小故障',
            type: 'error'
          }))
      }
    },
    handleSelectionChange (val) {
      this.multipleSelection = val
    },
    updateClick (row) {
      this.dialogUpdate = true
      // clone 'row' 引用的对象-使用新的clone对象.否则将复制真实对象的引用，操作的是真实对象，这将非常不安全的
      this.brand = JSON.parse(JSON.stringify(row))
    },
    deleteClick (row) {
      this.multipleSelection.push(row)
      this.deleteByIds()
    },
    deleteByIds () {
      if (this.multipleSelection.length === 0) {
        return
      }
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // 执行删除
        for (let i = 0; i < this.multipleSelection.length; i++) {
          // 将复选框获得的数据遍历，将得到的id存储到返回变量中去
          const el = this.multipleSelection[i]
          this.selectIds[i] = el.id
        }
        console.log(this.$qs.stringify({ ids: this.selectIds }, { arrayFormat: 'repeat' }))
        this.axios({
          method: 'post',
          url: '/delInfo',
          data: this.$qs.stringify({ ids: this.selectIds }, { arrayFormat: 'repeat' }) + '&' + this.$qs.stringify({ token: localStorage.getItem('token') })
        }).then(resp => {
          this.queryInfo()
          this.selectIds = []
          this.multipleSelection = []
          if (resp.data === 'success') {
            this.$message({
              showClose: true,
              message: '已成功删除信息',
              type: 'success'
            })
          } else {
            this.$message({
              showClose: true,
              message: '出了点小故障',
              type: 'error'
            })
          }
        })
      }).catch(_ => {
        // 取消删除
        this.selectIds = []
        this.multipleSelection = []
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    stateStr (row) {
      if (row.state === 0) {
        return 'disable'
      } else {
        return 'enable'
      }
    },
    checkWeek () {
      const d = new Date().getDay().toString()
      document.getElementById(d).className = 'week-bg'
    },
    queryInfo () {
      this.flag = !this.flag
      this.axios({
        method: 'post',
        url: '/queryInfo',
        data: this.$qs.stringify(this.brand) + '&' + this.$qs.stringify({ currentPage: this.currentPage, pageSize: this.pageSize }) + '&' + this.$qs.stringify({ token: localStorage.getItem('token') })
      }).then(resp => {
        this.tableData = resp.data.rows
        this.totalCount = resp.data.totalCount
      }).catch(error => {
        console.log(error.response)
      })
    },
    queryName () {
      this.axios({
        method: 'post',
        url: '/queryName',
        data: this.$qs.stringify({ email: localStorage.getItem('signEmail') }) + '&' + this.$qs.stringify({ token: localStorage.getItem('token') })
      }).then(resp => {
        this.username = resp.data
        console.log(this.username)
      }).catch(error => {
        console.log(error.response)
      })
    }
  },
  mounted () {
    this.checkWeek()
    this.queryName()
  }
}
</script>

<style>
body {
  --el-color-primary: #1279d2;
  --el-color-primary-light-3: #135187;
  --el-color-primary-light-9: #351B68;
  --el-bg-color-overlay: #1e2454;
  --el-fill-color-light: #37276d;
  --el-text-color-regular: #BABEC6;
  --el-border-color-light: none;
  --el-color-danger-light-5: none;
  --el-border-color: #979ace;
  --el-bg-color: #171C40;
  --el-fill-color-blank: none;
  --el-color-primary-light-5: none;
  --el-border-color-lighter: #1e2454;
  --el-text-color-primary: #BABEC6;
}
</style>
<style scoped>
.avatar-style:hover{
  box-shadow: 0 0 20px rgb(175, 175, 220);
}
.mis-enter-active,.mis-leave-active {
  transition: opacity,transform 0.5s,1s ease;
}
.mis-enter-from,
.mis-leave-to {
  opacity: 0;
  transform: translateX(150%);
}
.fontB{
  width: 100%;
  padding: 5px 0;
  color: #BABEC6;
  font-size: 15px;
  text-align: center;
}
.demo-form-inline{
  margin-top: 20px;
  --el-text-color-regular: #BABEC6;
  --el-fill-color-blank: transparent;
  --el-color-primary: #135187;
  --el-color-primary-light-3: #2f86d0;
}
.mis-head{
  display: flex;
  align-items: center;
  position: absolute;
  height: 60px;
  padding-left: 30px;
  margin-top: 10px;
  z-index: 3;
  border-radius: 10px;
  background-color: #171C40;
  box-shadow: 8px 8px 5px rgba(1, 1, 39, 0.4);
}
.mis-active{
  display: flex;
  justify-content: space-between;
  position: absolute;
  margin-top: 90px;
  width: 1200px;
  height: 50px;
  z-index: 3;
}
.mis-content{
  position: absolute;
  top: 145px;
  height: 800px;
  width: 1200px;
  z-index: 2;
  border: solid #171C40;
  border-radius: 10px;
  box-shadow: 8px 8px 5px rgba(1, 1, 39, 0.4);
}
.mis-side {
  position: absolute;
  z-index: 1;
  top: 200px;
  width: 1300px;
  height: 980px;
  display: flex;
  justify-content: center;
  border-radius: 8px;
}
#mis-table{
  height: 800px;
  width: 1200px;
  --el-table-header-bg-color: #171C40;
  --el-table-tr-bg-color: #171C40;
  --el-table-row-hover-bg-color: #28296d;
  --el-table-border-color: #0E0E23;
  --el-table-text-color: #BABEC6;
  --el-table-header-text-color: #BABEC6;
  --el-table-bg-color: #171C40;
  --el-fill-color-blank: transparent;
  --el-fill-color-lighter: #1e244f;
}
.active-button{
  width: 60px;
  height: 30px;
  border: none;
  margin: 5px 0;
  --el-color-danger: #9c1542;
  --el-color-primary: #135187;
  --el-color-primary-light-3: #2f86d0;
  --el-color-danger-light-3: #dc3e73;
}
.side-active .active-button{
  width: 100px;
  height: 40px;
}
/*#171C40*/
#rightSide{
  border: solid;
  display: block;
  animation: upSet 1.5s;
  position: relative;
  z-index: 20;
  right: 20px;
  width: 150px;
  height: 90%;
  border-radius: 10px;
  background-color: #171C40;
  box-shadow: 8px 8px 5px rgba(1, 1, 39, 0.4);
}
.leftSIde-button{
  width: 150px;
  font-size: 16px;
  height: 60px;
  border: none;
  border-bottom: 2px solid #0E0E23;
  margin: 0;
  padding: 0;
  color: aliceblue;
  background-color: transparent;
  transition-duration: 0.3s;
}
button:hover{
  background-color: rgba(67, 24, 145, 0.6);
}
#left-content{
  width: 150px;
  height: 300px;
  text-align: center;
  position: relative;
  top: 105px;
}
#leftSide{
  animation: downSet 1.5s;
  position: relative;
  left: 20px;
  width: 150px;
  height: 400px;
  border-radius: 10px;
  background-color: #171C40;
  box-shadow: 8px 8px 5px rgba(1, 1, 39, 0.3);
}
#bothSide{
  /*border: solid;*/
  position: relative;
  display: flex;
  justify-content: space-between;
  float: left;
  width: 1900px;
}
#inside{
  width: 2000px;
  position: relative;
  margin: 0 20px;
  display: flex;
  overflow: hidden;
  justify-content: center;
}
#week-font{
  position: relative;
  z-index: 4;
  line-height: 50px;
  font-size: 16px;
}
.week-bg{
  position: absolute;
  z-index: 3;
  left: -20px;
  top: -100px;
  width: 250px;
  height: 250px;
  animation: bk-bg 2s infinite linear;
  background-image: linear-gradient(-225deg, #231557 0%, #44107A 29%, #de014a 100%);
}
#week-block{
  position: relative;
  z-index: 2;
  overflow: hidden;
  background-color: rgba(49, 49, 113, 0.1);
  margin: 10px;
  width: 130px;
  height: 50px;
  text-align: center;
  border-radius: 10px;
  backdrop-filter: blur(2px);
  box-shadow: 8px 8px 5px rgba(1, 1, 39, 0.3);
}
#week-content{
  animation: rightSet 1.5s;
  position: absolute;
  z-index: 1;
  top: 110px;
  height: 80px;
  display: grid;
  grid-template-columns: repeat(7, 1fr);
}
#topSide{
  display: flex;
  justify-content: space-between;
  align-items: center;
  animation: rightSet 1.5s;
  margin-top: 20px;
  background-color: #171C40;
  border-radius: 10px;
  width: 1900px;
  position: absolute;
  z-index: 10;
  box-shadow: 8px 8px 5px rgba(1, 1, 39, 0.3);
}
.topA{
  width: 180px;
  text-align: center;
  position: relative;
}
#outside{
  animation: bottomSet 1.5s;
  width: 2000px;
  position: relative;
  margin: 0 20px;
  display: flex;
  overflow: hidden;
  background: #0E0E23;
}
.fontA{
  color: aliceblue;
}
@keyframes bk-bg {
  0%   {transform:rotateZ(0deg)}
  100% {transform: rotateZ(360deg)}
}
@keyframes upSet {
  0%{opacity: 0;transform: translateY(50%);transition-timing-function: ease-out}
}
@keyframes rightSet {
  0%{opacity: 0;transform: translateX(50%);transition-timing-function: ease-out}
}
@keyframes downSet {
  0%{opacity: 0;transform: translateY(-50%);transition-timing-function: ease-out}
}
</style>
