<template>
    <div>
      <NavigationBar/>
        <div>
        <el-carousel  style="width: 100%;height: 700px;" class="custom-carousel">
        <el-carousel-item v-for="(item,index) in imageList" :key="index" style="width: 100%;height: 700px;">
            <img :src="getImagePath(item.content)" style="width: 100%;height: 700px;background: no-repeat;"/>
        </el-carousel-item>
        </el-carousel>
        </div>
        <div class="overlay" style="box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1)">
        <div>
            <h2>公司简介:</h2>
            <p style=" margin-bottom: 20px;text-indent: 4em;">开启财务自由之旅</p>
            <p style=" margin-bottom: 20px;text-indent: 4em;">在我们的平台上，您可以轻松申请小额贷款，满足您的紧急资金需求</p>
            <p style=" margin-bottom: 20px;text-indent: 4em;">我们提供简便、快捷的贷款服务，让您摆脱财务困境，实现梦想和计划</p>
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
                <span>purehub</span>
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
    </div>
</template>
<script>
import  NavigationBar from '../../components/NavigationBar.vue'
 export default{
    name:'HomePage',
    components:{
        NavigationBar
    },
    data(){
        return {
            imageList:[
          {
            content:"2.jpg"
          },
          {
            content:"3.jpg"
          },
          {
            content:"66.jpg"
          },
          {
            content:"4.jpg"
          },
          {
            content:"55.jpg"
          }
        ],
        cards: [
        { image: require('../../assets/11.jpg'), content: '<h2>信贷服务</h2><p>息费透明、利息可算<p>申请便捷、还款灵活<p>信息加密、安全保护<p>', isHovered: false },
        { image: require('../../assets/22.jpg'), content: '<h2>理财服务</h2><p>精选合规金融机构产品<p>多重安全保障<p>产品丰富 操作便捷<p>', isHovered: false },
        { image: require('../../assets/33.jpg'), content: '<h2>保险经纪服务</h2><p>大牌定制 安全放心<p>快捷理赔 专人服务<p>为自己、为爸妈、为子女增加一份安全保障<p>', isHovered: false },
        { image: require('../../assets/44.jpg'), content: '<h2>支付服务</h2><p>连接海量商户与用户<p>高效安全便捷<p>提供全方位支付解决方案<p>', isHovered: false },
        { image: require('../../assets/55.jpg'), content: '<h2>个人金融科技</h2><p>磐石一站式金科开放平台<p>智能语音机器人<p>与数百家银行及挂牌金融机构合作<p>', isHovered: false },
        { image: require('../../assets/3.jpg'), content: '<h2>供应链金融科技</h2><p>一站式服务 操作便捷<p>降低欺诈风险<p>确保操作真实性<p>', isHovered: false }
      ],
        }
    },
    methods:{
        getImagePath(item) {
      return require(`@/assets/${item}`);
    },
    showContent(card, status) {
      card.isHovered = status;
    },
    }
 }
</script>
<style scoped>
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