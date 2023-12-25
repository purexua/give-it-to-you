<template>
    <div  class="main">
    <el-card  shadow="hover">
    <div  style="text-align: center;display: flex;flex-direction: row;">
        <el-card shadow="hover" style="width: 120px;">
            <h2>{{num}}</h2>
            <span style="color: #666;">注册用户</span>
        </el-card>
        <el-card shadow="hover" style="width: 120px;margin-left: 100px;">
            <h2>18</h2>
            <span style="color: #666;"></span>
        </el-card>
     </div>
    </el-card>
      <el-card shadow="hover">
      <canvas ref="chart" style="width: 1000px; height: 400px;"></canvas>
     </el-card>
    </div>
  </template>
  
  <script>
  import { Chart } from 'chart.js/auto';
  import axios from 'axios';
  
  export default {
    data() {
      return {
        timer:null,
        num:null,
        chartData: {
          labels: [],
          datasets: [
            {
              label: '产品 使用 人数 ',
              data: [],
              backgroundColor: [
              'rgba(255, 99, 132, 0.2)',
            'rgba(255, 159, 64, 0.2)',
            'rgba(255, 205, 86, 0.2)',
            'rgba(75, 192, 192, 0.2)',
            'rgba(54, 162, 235, 0.2)',
            'rgba(153, 102, 255, 0.2)',
            'rgba(201, 203, 207, 0.2)',
            'rgba(255, 99, 132, 0.2)',
            'rgba(255, 159, 64, 0.2)',
            'rgba(255, 205, 86, 0.2)',
            'rgba(75, 192, 192, 0.2)',
            'rgba(54, 162, 235, 0.2)',
            'rgba(153, 102, 255, 0.2)',
            'rgba(201, 203, 207, 0.2)',
            'rgba(255, 99, 132, 0.2)',
            'rgba(255, 159, 64, 0.2)',
            'rgba(255, 205, 86, 0.2)',
            'rgba(75, 192, 192, 0.2)',
            'rgba(54, 162, 235, 0.2)',
            'rgba(153, 102, 255, 0.2)',
            'rgba(201, 203, 207, 0.2)'
              ],
              borderColor: [
              'rgb(255, 99, 132)',
            'rgb(255, 159, 64)',
            'rgb(255, 205, 86)',
            'rgb(75, 192, 192)',
            'rgb(54, 162, 235)',
            'rgb(153, 102, 255)',
            'rgb(201, 203, 207)',
            'rgb(255, 99, 132)',
            'rgb(255, 159, 64)',
            'rgb(255, 205, 86)',
            'rgb(75, 192, 192)',
            'rgb(54, 162, 235)',
            'rgb(153, 102, 255)',
            'rgb(201, 203, 207)',
            'rgb(255, 99, 132)',
            'rgb(255, 159, 64)',
            'rgb(255, 205, 86)',
            'rgb(75, 192, 192)',
            'rgb(54, 162, 235)',
            'rgb(153, 102, 255)',
            'rgb(201, 203, 207)'
              ],
              borderWidth: 1
            }
          ]
        }
      };
    },
    mounted() {
      this.usrNum();
      this.fetchChartData()
        .then(data => {
            let items = data.data; 
			
            for (let item of items) {
                let type = item.productType;
                this.chartData.labels.push(type);
            }
          this.chartData.datasets[0].data = [1,5,6,7,3,11,2,5,7,6,5,4,3,2,1,5];
  
          this.renderChart();
        })
        .catch(error => {
          console.error('Failed to fetch chart data:', error);
        });
    },
    methods: {
      usrNum(){
          axios.get('http://localhost:3919/serve8080/count') // 替换为实际的后端接口地址
          .then(response => {
            this.num = response.data
          })
          .catch(error => {
            console.error('Failed to fetch chart data:', error);
          });
      },
      fetchChartData() {
        return axios.get('http://localhost:3919/serve8080/application/getAllType') // 替换为实际的后端接口地址
          .then(response => response.data)
          .catch(error => {
            console.error('Failed to fetch chart data:', error);
          });
      },
      renderChart() {
        const ctx = this.$refs.chart.getContext('2d');
        const chart = new Chart(ctx, {
          type: 'bar',
          data: this.chartData,
          options: {
            responsive: false,
            maintainAspectRatio: false,
            onClick: function(event, elements) {
                if (elements && elements.length > 0) {
                const index = elements[0].index;
                // 在这里触发你的事件，可以使用 index 获取被点击柱状图的索引
              
                console.log('柱状图被点击，索引为：', index);
                }
            },
            scales: {
              y: {
                beginAtZero: true,
                grid: {
                    display: false, // 隐藏 y 轴的网格线
                }
              },
              x: {
                barPercentage: 1, // 设置柱的宽度为横坐标间距的80%
                categoryPercentage: 1, // // 设置 labels 宽度为数据点宽度的60%
                grid: {
                    display: false, // 隐藏 y 轴的网格线
                }
            },
            }
          }
        });
      }
    }
  }
  </script>
  <style scoped>
  .main {
  background-color: #fff;
  border-radius: 4px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  padding: 16px;
}
</style>