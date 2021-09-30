<template>
  <div style="margin: 10px 0">
    <el-button type="primary" @click="add">新增</el-button>
  </div>
  <div>
    <el-input v-model="input" placeholder="Please input" style="width: 20%"/>
    <el-button type="primary">查询</el-button>
  </div>

</template>

<script>

import {ref} from "vue";

export default {
  name: "MyButtons",
  search:"",
  methods:{
    add(){

    }
  }
}
</script>

<style>

</style>