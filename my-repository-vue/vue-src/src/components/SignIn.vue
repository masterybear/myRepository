<template>
  <div class="outside outside-active">
    <!--  注册  -->
    <div class="leftSide">
      <form id="formA" method="post" name="signUp">
        <!--注册-->
        <div  class="boxA"><strong class="fontA">Sign Up!</strong></div>
        <!--用户名-->
        <div>
          <div class="boxD"><strong class="fontC">用户名</strong></div>
          <div><input class="username" type="text" v-model="user.username" name="username" @blur="signUpName"></div>
          <div id="error"><span id="fontError">{{error.signUp.username}}</span></div>
        </div>
        <!--邮箱-->
        <div>
          <strong class="fontC">邮箱</strong>
          <div><input class="email" type="email" v-model="user.email" name="email" @blur="signUpEmail"></div>
          <div id="error"><span id="fontError">{{error.signUp.email}}</span></div>
        </div>
        <!--密码-->
        <div>
          <strong class="fontC">密码</strong>
          <div><input class="password" type="password" v-model="user.password" name="password" @blur="signUpPas"></div>
          <div id="error"><span id="fontError">{{error.signUp.password}}</span></div>
        </div>
        <div id="sbm">
          <input type="button" value="Click" class="submit-button" @click="signUpCheck">
        </div>
      </form>
    </div>
    <!--  登录  -->
    <div class="rightSide">
      <form id="formB" method="post" name="signIn">
        <!--登录-->
        <div class="boxA"><strong class="fontA">Sign In</strong></div>
        <!--Welcome-->
        <div class="boxC"><span class="fontB">Welcome</span></div>
        <!--邮箱-->
        <div>
          <strong class="fontC">邮箱</strong>
            <input class="email" type="email" v-model="user.email" name="email" @blur="checkEmail">
            <div id="error"><span id="fontError">{{error.signIn.email}}</span></div>
        </div>
        <!--密码-->
        <div>
          <strong class="fontC">密码</strong>
          <div>
            <input class="password" type="password" v-model="user.password" name="password" @blur="checkPas">
            <div id="error"><span id="fontError">{{error.signIn.password}}</span></div>
          </div>
        </div>
        <!--记住我-->
        <div class="boxB">
          <input type="checkbox" v-model="remember" id="remember" @change="checkOnChange">
          <label for="remember" class="fontC">记住我</label>
        </div>
        <!--提交-->
        <div id="sbm">
          <input type="button" value="Click" class="submit-button" @click="signInCheck"/>
        </div>
      </form>
    </div>
    <div class="overlay">
      <div class="overlay-panel">
          <button class="btn" id="signUp" @click="SignUpBtm">去注册</button>
          <button class="btn" id="signIn" @click="SignInBtm">去登录</button>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: 'SignIn',
  data () {
    return {
      remember: false,
      error: {
        signUp: {
          username: '',
          email: '',
          password: ''
        },
        signIn: {
          email: '',
          password: ''
        }
      },
      user: {
        username: '',
        email: '',
        password: ''
      }
    }
  },
  methods: {
    checkEmail () {
      const email = document.signIn.email.value.replace(' ', '')
      const emailReg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/
      if (emailReg.test(email)) {
        this.error.signIn.email = ''
        return true
      } else {
        this.error.signIn.email = '请注意邮箱格式是否正确'
        return false
      }
    },
    checkPas () {
      const pas = document.signIn.password.value.replace(' ', '')
      if (!(pas.length < 6) && !(pas.length > 20)) {
        this.error.signIn.password = ''
        return true
      } else {
        this.error.signIn.password = '密码应介于6-20个字符之间'
        return false
      }
    },
    signUpName () {
      const name = document.signUp.username.value.replace(' ', '')
      if (!(name.length < 2) && !(name.length > 20)) {
        this.error.signUp.username = ''
        return true
      } else {
        this.error.signUp.username = '用户名应介于2-20个字符之间'
        return false
      }
    },
    signUpEmail () {
      const email = document.signUp.email.value.replace(' ', '')
      const emailReg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/
      if (emailReg.test(email)) {
        this.error.signUp.email = ''
        return true
      } else {
        this.error.signUp.email = '请注意邮箱格式是否正确'
        return false
      }
    },
    signUpPas () {
      const pas = document.signUp.password.value.replace(' ', '')
      if (!(pas.length < 6) && !(pas.length > 20)) {
        this.error.signUp.password = ''
        return true
      } else {
        this.error.signUp.password = '密码应介于6-20个字符之间'
        return false
      }
    },
    SignInBtm () {
      document.querySelector('.outside').classList.add('outside-active')
    },
    SignUpBtm () {
      document.querySelector('.outside').classList.remove('outside-active')
    },
    checkInfo () {
      this.user.username = this.user.username.replace(/\s+/g, '')
      this.user.email = this.user.email.replace(/\s+/g, '')
      this.user.password = this.user.password.replace(/\s+/g, '')
    },
    signUpCheck (event) {
      if (this.signUpName() && this.signUpEmail() && this.signUpPas()) {
        this.checkInfo()
        this.axios({
          method: 'post',
          url: '/signUp',
          data: this.$qs.stringify(this.user)
        }).then(resp => {
          if (resp.data === 'success') {
            // 注册成功
            // this.$router.push('/sign')
            this.$message({
              type: 'success',
              message: '注册成功'
            })
          } else if (resp.data === 'emailErr') {
            this.error.signUp.email = '邮箱已被使用'
          } else if (resp.data === 'Unavailable') {
            this.error.signUp.username = '用户名已被使用'
          } else {
            this.error.signUp.username = '未知错误'
          }
        })
      } else {
        event.preventDefault()
      }
    },
    signInCheck (event) {
      if (this.checkEmail() && this.checkPas()) {
        this.checkInfo()
        this.axios({
          method: 'post',
          url: '/signIn',
          data: this.$qs.stringify({ token: localStorage.getItem('token'), email: this.user.email, password: this.user.password })
        }).then(resp => {
          if (resp.data.signInfo === 'validToken' || resp.data.signInfo === 'invalidToken') {
            // 是否记住密码
            if (this.remember) {
              localStorage.setItem('signEmail', this.user.email)
              localStorage.setItem('signPwd', this.user.password)
            } else {
              localStorage.removeItem('signEmail')
              localStorage.removeItem('signPwd')
            }
            // 查验token
            if (resp.data.signInfo === 'invalidToken') {
              const token = resp.data.token
              localStorage.setItem('token', token)
              this.$router.push('/home')
            } else {
              this.$router.push('/home')
            }
          } else if (resp.data.signInfo === 'pwdErr') {
            this.error.signIn.password = '密码不正确'
          } else {
            this.error.signIn.email = '未注册'
          }
        })
      } else {
        event.preventDefault()
      }
    }
  },
  mounted () {
    if (localStorage.getItem('signEmail')) {
      this.user.email = localStorage.getItem('signEmail')
      this.user.password = localStorage.getItem('signPwd')
      this.remember = true
    }
  }
}
</script>

<style scoped>
#fontError{
  font-size: 10px;
  color: #e5043b;
}
#error{
  left: 40px;
  position: relative;
  width: 250px;
  overflow: hidden;
  height: 20px;
}
.outside.outside-active .overlay{
  left: 0;
}
.overlay{
  transition: left 0.6s;
  left: 50%;
  width: 50%;
  height: 100%;
  position: absolute;
  overflow: hidden;
}
.outside.outside-active .overlay-panel{
  left: 38%;
}
.overlay-panel{
  left: -62%;
  top: 45%;
  width: 125%;
  display: flex;
  justify-content: space-between;
  position: absolute;
}
/*包装盒*/
.outside{
  width: 616px;
  height: 420px;
  position: relative;
  overflow: hidden;
  display: flex;
  justify-content: space-between;
  border-radius: 10px;
  background-color: rgba(27, 27, 63, 0.2);
  backdrop-filter: blur(6px);
  box-shadow: 8px 8px 5px rgba(1, 1, 39, 0.3);
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}
#formA,#formB{
  top: 0;
  position: absolute;
}
.leftSide {
  /*transition: transform 0.6s;*/
  transition-property: opacity, transform;
  transition-duration: 0.4s, 0.7s;
  opacity: 100%;
  transform: translateX(0);
  width: 258px;
  padding: 25px;
}
.outside.outside-active .leftSide{
  opacity: 0;
  transform: translateX(-150%);
}
.rightSide{
  /*transition: transform 0.6s;*/
  transition-property: opacity, transform;
  transition-duration: 0.4s, 0.7s;
  opacity: 0;
  transform: translateX(150%);
  width: 258px;
  padding: 25px;
}
.outside.outside-active .rightSide{
  opacity: 100%;
  transform: translateX(0);
}
/*标题*/
.boxA{
  margin-top: 20px;
}
/*记住我*/
.boxB{
  margin-top: 5px;
}
/*Welcome*/
.boxC{
  margin-top: 20px;
  margin-bottom: 20px;
}
.boxD{
  margin-top: 20px;
}
/*欢迎使用*/
.fontA{
  font-size: 35px;
  color: aliceblue;
  text-shadow: 5px 5px 3px rgba(1, 1, 53, 0.55);
}
/*Welcome*/
.fontB{
  font-size: 15px;
  color: darkgray;
}
/*邮箱与密码*/
.fontC{
  font-size: 15px;
  color: aliceblue
}
/*登录与注册*/
#sbm{
  width: 250px;
  position: fixed;
  bottom: 20px;
  display: flex;
  justify-content: center;
}
.submit-button,.btn{
  box-shadow: 4px 8px 19px -3px rgba(0,0,0,0.3);
  font-weight: bold;
  height: 35px;
  width: 80px;
  border-radius: 5px;
  border: none;
  background-color: #4B70E2;
  color: white;
  transition-duration: 0.3s;
}
.submit-button:hover,.btn:hover{
  background-color: white;
  color: #4B70E2;
}
#remember{
  width: 16px;
  height: 16px;
  vertical-align: middle;
}
input[type=text],input[type=email],input[type=password]{
  height: 30px;
  width: 250px;
  color: aliceblue;
  font-size: 15px;
  border-radius: 8px;
  margin-top: 10px;
  border: 2px solid rgba(184, 175, 190, 0.3);
  background-color: transparent;
  transition-duration: 0.4s;
}
.username:focus,.password:focus,.email:focus{
  outline: none;
  border: 2px solid rgb(226, 215, 233);
}
.username:hover,.password:hover,.email:hover{
  border: 2px solid rgb(226, 215, 233);
}
</style>
