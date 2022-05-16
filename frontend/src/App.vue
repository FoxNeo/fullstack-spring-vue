<template>
 <div class="common-layout">
  <el-container>
    <el-main>
      <todo-list></todo-list>
    </el-main>
  </el-container>
  </div>
</template>

<script lang="ts">
import { Options, Vue } from "vue-class-component";
import TodoList from "./components/TodoList.vue";

@Options({
  components: {
    TodoList,
  },
})
export default class App extends Vue {}
</script>

<style>
body {
  margin: 0;
}
.el-container {
  background-color: #e9eef3;
  color: var(--el-text-color-primary);
  text-align: center;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
}
</style>
