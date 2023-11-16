<template>
  <div id="app">
    <div>
      <el-carousel  style="width: 1500px;height: 700px;" class="custom-carousel">
      <el-carousel-item v-for="(item,index) in imageList" :key="index" style="width: 1500px;height: 700px;">
        <img :src="getImagePath(item.content)" style="width: 1500px;height: 700px;background: no-repeat;"/>
      </el-carousel-item>
      </el-carousel>
    </div>
    <div class="header">
      <img src="./assets/logo-plus.png" style="height: 75px; position: relative; left: 100px; margin-right: 230px;">
      <AdminView/>
      <el-button  icon="el-icon-service" type = "text" class="button-onlie">在线客服</el-button>
      <el-button  icon="el-icon-download" type = "text" class="button-download">下载</el-button>
      <el-button  icon="el-icon-user-solid" type = "text" class="button-admin" @click="admin">登录</el-button>
    </div>
    <div class="overlay" style="box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1)">
      <div>
        <h2>公司简介:</h2>
        <p style=" margin-bottom: 20px;text-indent: 4em;">《原神》是由米哈游自研的一款全新开放世界冒险RPG。你将在游戏中探索一个被称作「提瓦特」的幻想世界。 </p>
        <p style=" margin-bottom: 20px;text-indent: 4em;"> 在这广阔的世界中，你可以踏遍七国，邂逅性格各异、能力独特的同伴，与他们一同对抗强敌，踏上寻回血亲之路；</p>
        <p style=" margin-bottom: 20px;text-indent: 4em;">也可以不带目的地漫游，沉浸在充满生机的世界里，让好奇心驱使自己发掘各个角落的奥秘⋯⋯ </p>
        <p style=" margin-bottom: 20px;text-indent: 4em;">直到你与分离的血亲重聚，在终点见证一切事物的沉淀。</p>
      </div>
    </div>
    <div class="bottom" style="box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1)">
      <div class="card-row" v-for="(row, index) in Math.ceil(cards.length / 3)" :key="index">
        <div class="card"
           v-for="(card, i) in cards.slice(index * 3, (index + 1) * 3)"
           :key="i"
           :style="{ 
             backgroundImage: `url(${card.image})`
           }"
           @mouseenter="showContent(card, true)"
           @mouseleave="showContent(card, false)">
        <div v-if="card.isHovered" class="card-content">
          <p v-html=card.content></p>
        </div>
        </div>
      </div>
    </div>
    <div>
      <el-dialog
          title="用户登录"
          :visible.sync="outerVisible"
          width="25%">
          <div>
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="70px" class="demo-ruleForm">
                <el-form-item label="用户名" prop="name">
                  <el-input v-model="ruleForm.id"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                  <el-input v-model="ruleForm.password"></el-input>
                </el-form-item>
            </el-form>
          </div>
          <div>
          <el-dialog
              width="30%"
              title="用户注册"
              :visible.sync="innerVisible"
              append-to-body>
          </el-dialog>
        </div>
          <span slot="footer" class="dialog-footer" style="display: flex;flex-direction: column;margin-top: -40px;">
            <el-button type="text" @click="innerVisible = true">没有账号去注册</el-button>
            <el-button type="danger" @click="dialogVisible = false" style="position: relative;left: 35%;width: 100px;">登 录</el-button>
        </span>
        </el-dialog>
    </div>
    <div class="contain">
      <img :src="require(`@/assets/2.jpg`)" style="width: 1350px;height: 500px;background: no-repeat;"/>
    </div>
    <div class="contain-in">
        <h2>我们的使命</h2>
        <p>让天下没有难做的生意</p>
        <h2>我们的愿景</h2>
        <p>我们的愿景是构建未来服务业的数字化基础设施，为世界带来更多微小而美好的改变。</p>
        <p>我们追求成为一家健康成长102年的好公司</p>
        <p>我们希望,</p>
        <p>每一个个体可以享受到普惠、绿色的金融服务；</p>
        <p>每一家小微企业拥有平等的发展机会；</p>
        <p>通过开放合作，让数字生活触手可及</p>
      </div>
      <div class="bottom-main">
        <div class="contain-div">
          <div class="div">
            <h2>总部地址</h2>
            <span>栎一425</span>
          </div>
          <div class="div">
            <h2>联系我们</h2>
            <span>热线电话: 10086</span>
          </div>
          <div class="div">
            <h2>重要报告</h2>
            <span>可持续发展报告</span>
          </div>
          <div class="div">
            <h2>关联平台</h2>
            <span>原神爱好者协会</span>
          </div>
        </div>
        <hr style="width: 1000px;margin-left: 150px;margin-top: 100px;">
        <div class="contain-div">
          <div class="div-final">
            <span>《给你呗集团隐私政策》</span><br>
            <span>《给你呗集团客户隐私承诺书》</span><br>
            <span>《给你呗集团关于保护个人信息的平台规则》</span>
          </div>
          <div class="div-final-two">
            <span>经营许可证编号: </span><br>
            <span>ICP备案: </span><br>
            <span>@给你呗科技集团股份有限公司</span>
          </div>
        </div>
      </div>
    <el-backtop></el-backtop>
  </div>
</template>

<script>
import AdminView from '../src/views/AdminView.vue'

export default {
  name: 'App',
  components: {
    AdminView
  },
  data(){
      return {
        imageList:[
          {
            content:"2.jpg"
          },
          {
            content:"2.jpg"
          },
          {
            content:"3.png"
          },
          {
            content:"4.jpg"
          },
          {
            content:"55.png"
          }
        ],
        cards: [
        { image: require('./assets/11.jpg'), content: '<ul><li><h2>信贷服务</h2><li><li><p>息费透明、利息可算<p></li><li>申请便捷、还款灵活</li><li><p>信息加密、安全保护<p></li></ul>', isHovered: false },
        { image: require('./assets/22.jpg'), content: '<ul><li><h2>理财服务</h2><li><li><p>精选合规金融机构产品<p></li><li>多重安全保障</li><li><p>产品丰富 操作便捷<p></li></ul>', isHovered: false },
        { image: require('./assets/33.png'), content: '<ul><li><h2>保险经纪服务</h2><li><li><p>大牌定制 安全放心<p></li><li>快捷理赔 专人服务</li><li><p>为自己、为爸妈、为子女增加一份安全保障<p></li></ul>', isHovered: false },
        { image: require('./assets/44.png'), content: '<ul><li><h2>支付服务</h2><li><li><p>连接海量商户与用户<p></li><li>高效安全便捷</li><li><p>提供全方位支付解决方案<p></li></ul>', isHovered: false },
        { image: require('./assets/55.png'), content: '<ul><li><h2>个人金融科技</h2><li><li><p>磐石一站式金科开放平台<p></li><li>智能语音机器人</li><li><p>与数百家银行及挂牌金融机构合作<p></li></ul>', isHovered: false },
        { image: require('./assets/33.png'), content: '<ul><li><h2>供应链金融科技</h2><li><li><p>一站式服务 操作便捷<p></li><li>降低欺诈风险</li><li><p>确保操作真实性<p></li></ul>', isHovered: false }
      ],
      outerVisible: false,
    innerVisible: false,
      ruleForm: {
          id: '',
          password:''
        },
        rules: {
          name: [
            { required: true, message: '请输入用户名', trigger: 'blur' },
            { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '请输入密码', trigger: 'blur' },
            { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
          ],
        }
      }
  },
  methods: {
    getImagePath(item) {
      return require(`@/assets/${item}`);
    },
    showContent(card, status) {
      card.isHovered = status;
    },
      admin(){
        this.outerVisible = true;
      }
  },
}
</script>

<style>
.div-final-two{
  position: relative;
  margin-left: 500px;
  margin-top: 2%;
  display: flex;
  flex-direction: column;
  font-size: 10px;
}
.div-final{
  position: relative;
  margin-left: 150px;
  margin-top: 2%;
  display: flex;
  flex-direction: column;
  font-size: 10px;
}
.contain-div{
  display: flex;
  flex-direction: row;
}
.div{
  position: relative;
  margin-left: 150px;
  margin-top: 2%;
}
.bottom-main{
  position: relative;
  margin-top: 100px;
  width: 100%;
  height: 600px;
  background-color:rgb(12, 31, 58);
  color: white;
}
.contain-in{
  text-align: center;
    z-index: 1;
    position: relative;
    margin-top: -450px;
    margin-left: 30%;
    width: 40%;
    height: 400px;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    background-color: white;
    opacity: 1;
}
.contain{
  position: relative;
  margin-top: 100px;
  margin-left: 5%;
  width: 1300px;
  height: 500px;
  opacity: 0.4;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  z-index: -1;
}
.button-admin{
  position: absolute;
  top: 25px;
  left: 89%;
  color: white;
  font-size: 20px;
}
.button-download{
  position: absolute;
  top: 25px;
  left: 82%;
  color: white;
  font-size: 20px;
}
.button-onlie{
  position: absolute;
  top: 25px;
  left: 73%;
  color: white;
  font-size: 20px;
}
ul li{
  position: relative;
  left: -40px;
}
li {
  list-style-type: none; /* 清除默认的列表样式 */
}
.card {
  position: relative;
  background-size: cover;
  background-position: center;
  width: 30%;
  height: 280px;
  margin: 10px;
  display: inline-block;
}

.card-content {
  position: absolute;
  opacity: 0.5;
  width: 200px;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: rgba(255, 255, 255, 0.8);
  padding: 10px;
  display: none;
}

.card:hover .card-content {
  display: block;
}

.card-row {
  text-align: center;
}
.bottom{
  position: relative;
  margin-top: 50px;
  margin-left: 10%;
  width: 80%;
  height: 600px;
}
.custom-carousel{
  z-index: -1;
}
.header {
  display: flex;
  width: 90%;
  position: absolute;
  top: 75px;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: rgba(255, 255, 255, 0.7);
  padding: 20px;
  text-align: center;
  z-index: 1; 
}
.overlay {
  position: relative;
  margin-top: 50px;
  margin-left: 20%;
  width: 60%;
  height: 200px;
}

.el-carousel__item h3 {
    color: #475669;
    font-size: 18px;
    opacity: 0.75;
    line-height: 300px;
    margin: 0;
  }
  
  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }
  
  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }

</style>
