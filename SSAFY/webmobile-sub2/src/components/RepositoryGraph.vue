<template>
    <apexchart height=350 :options="chartOptions" :series="series"/>
</template>
<script>
import Api from "@/services/GitlabService";
import VueApexCharts from 'vue-apexcharts'
export default{
    data(){
        return{
            commits: [],
            series:[
            {
                name: "박한범",
                type: 'line',
                data: []
            },{
                name: "오명현",
                type: 'line',
                data: []
            },{
                name: "유창오",
                type: 'line',
                data: []
            },{
                name: "정태현",
                type: 'line',
                data: []
            },{
                name: "김동욱",
                type: 'line',
                data: []
            }],
            chartOptions:{
                chart:{
                    height: 350,
                    type: 'line'
                },
                stroke:{
                    curve: 'smooth'
                },
                
                grid:{
                    row:{
                        colors: ['#f3f3f3','transparent'],
                        opacity: 0.5
                    }
                },
                xaxis:{
                    categories:[]
                },
                yaxis:[
                    {
                        opposite: true,
                        title:{
                            text: 'each'
                        }
                    }
                ]
            }
        }
    },
    components:{
        'apexchart': VueApexCharts
    },
    mounted(){
        this.setCommits()
    },
    methods:{
        async setCommits(){
            const response = await Api.getCommits(6016)
            this.commits = response
            console.log(commits)
            const commits = this.commits;
            const com1 = {};
            const com2 = {};
            const com3 = {};
            const com4 = {};
            const com5 = {};
            var num = commits.length -1
            const date = new Date(commits[num].created_at);
            var ddate = date.toISOString().substring(0,10);      
            var current = commits[num].created_at.substring(0,10)
            const startdate = ddate.substring(5,10)
            var now = new Date();
            var diff = Math.abs(now.getTime() - date.getTime())
            diff = Math.ceil(diff/(1000*3600*24))
            for(var i = 0;i < diff;i++){
                var day = ddate.substring(5,10)
                com1[day] = 0;
                com2[day] = 0;
                com3[day] = 0;
                com4[day] = 0;
                com5[day] = 0;
                while(ddate == current){
                    if(commits[num].action_name == "pushed to"){
                        var id = commits[num].author_id;
                        if(id == 336){
                            com1[day] ++;
                        }else if(id == 281){
                            com2[day] ++;
                        }else if(id == 345){
                            com3[day] ++;
                        }else if(id == 367){
                            com4[day] ++;
                        }else if(id == 291){
                            com5[day] ++;
                        }
                    }
                    num--;
                    if(num < 0)
                        break;
                    current = commits[num].created_at.substring(0,10);
                }
                date.setDate(date.getDate()+1);
                ddate = date.toISOString().substring(0,10);
            }
            const latest = current.substring(5,10)
            this.chartOptions = {
                title:{
                    text: 'Number of commits from '+startdate+' to '+latest,
                    align: 'left'
                },
                xaxis:{
                    categories: Object.keys(com1)
                }
            }
            this.series=[
            {
                data: Object.values(com1)
            },{
                data: Object.values(com2)
            },{
                data: Object.values(com3)
            },{
                data: Object.values(com4)
            },{
                data: Object.values(com5)
            }]
        }
    }
}
</script>