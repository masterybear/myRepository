<template>
  <div class="Home">
    <HomePage/>
  </div>
</template>

<script>
import HomePage from '@/components/HomePage'
export default {
  name: 'SignView',
  components: { HomePage }
}
</script>

<style scoped>
.Home{
  background: #1B0641;
  width:100%;
  height:100%;
  position:fixed;
  background-size:100% 100%;
  display: flex;
  justify-content: center;
  /*align-items: center;*/
}
</style>
