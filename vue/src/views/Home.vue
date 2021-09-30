<template>
  <div style="margin: 10px 0">
    <el-button type="primary" @click="add">新增</el-button>
  </div>
  <div>
    <el-input v-model="search" placeholder="Please input" style="width: 20%"/>
    <el-button type="primary" @click="load">查询</el-button>
  </div>
  <el-table :data="tableData" style="width: 100%">
    <el-table-column fixed prop="id" label="id" width="100" />
    <el-table-column prop="name" label="name" width="100" />
    <el-table-column prop="age" label="age" width="100" />
    <el-table-column prop="course" label="course" width="100" />
    <el-table-column prop="fraction" label="fraction" width="100" />
    <el-table-column fixed="right" label="Operations" width="120">
      <template #default="scope">
        <el-button type="text" size="small" @click="handleClick(scope.row)">编辑
        </el-button>
        <el-button type="text" size="small" @click="handleDelete(scope.row.id)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
  <div>
    <el-pagination
        v-model:currentPage="currentPage"
        :page-sizes="[5, 10, 20, 50]"
        v-model:pageSize="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="10"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
    >
    </el-pagination>
    <el-dialog v-model="dialogVisible" title="Tips" width="30%">
      <el-form ref="form" :model="form" label-width="120px">
        <el-form-item label="name">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="age">
          <el-input v-model="form.age"></el-input>
        </el-form-item>
        <el-form-item label="course">
          <el-input v-model="form.course"></el-input>
        </el-form-item>
        <el-form-item label="fraction">
          <el-input v-model="form.fraction"></el-input>
        </el-form-item>

      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="save"
        >Confirm</el-button
        >
      </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
// @ is an alias to /src

import request from "../utils/request";

export default {
  name: 'Home',

  data() {
    return {
      dialogVisible: false,
      currentPage: 1,
      total:10,
      pageSize:10,
      search:"",
      form:{},
      tableData: []
    }
  },
  created(){
    this.load()
  },
  methods:{
    add(){
      this.dialogVisible = true
      this.form = {}
    },
    load(){
      request.get("/api/user/findPage",{
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search: this.search
        }
      }).then(res=>{
        console.log(res)
        this.tableData = res.data.records
      })
    },
    save(){
      if(this.form.id){ //更新
        request.put("/api/user/update", this.form).then(res=>{
          console.log(res);
          if(res.code === '0'){
            this.$messageBox({
              type:"success",
              message:"更新成功"
            })
          }else{
            this.$messageBox({
              type:"error",
              message:res.msg
            })
          }
          this.load()
          this.dialogVisible = false
        })
      }else{
        this.dialogVisible = false
        request.post("/api/user/save", this.form).then(res=>{
          console.log(res);
          if(res.code === '0'){
            this.$messageBox({
              type:"success",
              message:"新增成功"
            })
          }else{
            this.$messageBox({
              type:"error",
              message:res.msg
            })
          }
          this.load()
          this.dialogVisible = false
        })
      }


    },
    handleClick(row){
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogVisible = true
    },
    handleDelete(id){
      request.delete("/api/user/delete/" + id).then(res=>{
        if(res.code === '0'){
          if(res.code === '0'){
            this.$messageBox({
              type:"success",
              message:"删除成功"
            })
          }else{
            this.$messageBox({
              type:"error",
              message:res.msg
            })
          }
          this.load()
        }
      })
    },
    handleSizeChange(){

    },
    handleCurrentChange(){

    }
  }
}
</script>
