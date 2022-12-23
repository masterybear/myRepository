<template>
  <div class="Sign">
    <SignIn/>
  </div>
</template>

<script>
import SignIn from '@/components/SignIn'
export default {
  name: 'SignView',
  components: { SignIn }
}
</script>

<style scoped>
.Sign{
  background: url("../assets/flyline.png");
  width:100%;
  height:100%;
  position:fixed;
  background-size:100% 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
