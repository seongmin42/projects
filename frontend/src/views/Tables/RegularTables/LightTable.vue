<template>
    <b-card no-body>
        <b-card-header class="border-0">
            <h3 class="mb-0">오늘의 날씨 정보</h3>
        </b-card-header>

        <v-fortag v-for="info in infos.response.body.items" :key="info.id">
            <el-table class="table-responsive table" header-row-class-name="thead-light" :data="projects">
                
                <el-table-column label="지역" min-width="150px" prop="info.stationName">
                    <template v-slot="{ row }">
                        <b-media no-body class="align-items-center">
                            <b-media-body>
                                <span class="font-weight-600 name mb-0 text-sm">{{ info.stationName }}</span>
                            </b-media-body>
                        </b-media>
                    </template>
                </el-table-column>

                <el-table-column label="미세먼지 등급" min-width="150px" prop="pm10Grade">
                    <template v-slot="{ row }">
                        <badge class="badge-dot mr-4" type="" style="color:black">
                            <i :class="`bg-${colorStatus(info.pm10Grade)}`"></i>
                            {{ grading(info.pm10Grade) }} ({{ info.pm10Value }})
                            <br>
                            <!--
                        <span class="status" :class="`text-${row.statusType}`">{{row.pm10Grade}}</span>
                        -->
                        </badge>
                    </template>
                </el-table-column>

                <el-table-column label="초미세먼지 등급" min-width="150px" prop="pm25Grade">
                    <template v-slot="{ row }">
                        <badge class="badge-dot mr-4" type="" style="color:black">
                            <i :class="`bg-${colorStatus(info.pm25Grade)}`"></i>
                            {{ grading(info.pm25Grade) }} ({{ info.pm25Value }})
                            <br>
                        </badge>
                    </template>
                </el-table-column>

                <el-table-column label="오존 등급" min-width="150px" prop="o3Grade">
                    <template v-slot="{ row }">
                        <badge class="badge-dot mr-4" type="" style="color:black">
                            <i :class="`bg-${colorStatus(info.o3Grade)}`"></i>
                            {{ grading(info.o3Grade) }} ({{ info.o3Value }})
                            <br>
                        </badge>
                    </template>
                </el-table-column>

            </el-table>
        </v-fortag>
    </b-card>
</template>



<script>
import projects from "./../projects";
import { Table, TableColumn } from "element-ui";
export default {
  name: "light-table",
  components: {
    [Table.name]: Table,
    [TableColumn.name]: TableColumn,
  },
  data() {
    return {
      projects,
      infos: {},
      url_base: "https://apis.data.go.kr/B552584/ArpltnInforInqireSvc",
    };
  },
  created() {
    let fetchUrl = `${this.url_base}/getCtprvnRltmMesureDnsty?sidoName=%EC%84%9C%EC%9A%B8&pageNo=1&numOfRows=100&returnType=json&serviceKey=${process.env.VUE_APP_FINE_DUST_API_KEY}&ver=1.0`;
    fetch(fetchUrl)
      .then((response) => {
        return response.json();
      })
      .then((result) => {
        this.infos = result; // 미세먼지 관련 정보 pm10 미세먼지, pm25 초미세먼지
        console.log(result.response.body.items);
      });
  },
  mounted() {},
  methods: {
    grading(grade) {
      switch (grade) {
        case "1":
          return "좋음";
        case "2":
          return "보통";
        case "3":
          return "나쁨";
        case "4":
          return "아주 나쁨";
        default:
          return "정보 없음";
      }
    },
    colorStatus(grade) {
      switch (grade) {
        case "1":
          return "info";
        case "2":
          return "success";
        case "3":
          return "warning";
        case "4":
          return "danger";
        default:
          return "dark";
      }
    },
  },
};
</script>
