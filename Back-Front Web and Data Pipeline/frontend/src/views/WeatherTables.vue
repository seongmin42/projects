<template>
  <div>
    <base-header class="pb-6 pb-8 pt-5 pt-md-8 bg-gradient-success">
      <!-- Card stats -->
      <b-row>
        <b-col xl="3" md="6">
          <stats-card
            title="Your Location"
            type="gradient-red"
            :sub-title="weather.name"
            icon="ni ni-square-pin"
            class="mb-4"
          >
            <template slot="footer">
              <span class="text-danger mr-2"> </span>
            </template>
          </stats-card>
        </b-col>
        <b-col xl="4" md="6">
          <stats-card
            title="Today's Weather"
            type="gradient-orange"
            :sub-title="weather.weather[0].main"
            icon="ni ni-air-baloon"
            class="mb-4"
          >
            <template slot="footer">
              <span class="text-danger mr-2"> </span>
            </template>
          </stats-card>
        </b-col>
        <b-col xl="5" md="6">
          <stats-card
            title="Temparature"
            type="gradient-green"
            :sub-title="
              (Math.round(Celsius(temp) * 10) / 10).toString().concat('℃')
            "
            icon="ni ni-sound-wave"
            class="mb-4"
          >
            <template slot="footer">
              <span class="text-danger mr-2"
                >최저기온 :
                {{
                  Math.round(Celsius(weather.main.temp_min) * 10) / 10
                }}℃</span
              >
              <span class="text-nowrap"
                >최고기온 :
                {{
                  Math.round(Celsius(weather.main.temp_max) * 10) / 10
                }}℃</span
              >
            </template>
          </stats-card>
        </b-col>
      </b-row>
    </base-header>
    <b-container fluid class="mt--7">
          <light-table></light-table>
      <div class="mt-5"></div>
    </b-container>
  </div>
</template>
<script>
import {
  Dropdown,
  DropdownItem,
  DropdownMenu,
  Table,
  TableColumn,
} from "element-ui";
import projects from "./Tables/projects";
import users from "./Tables/users";
import LightTable from "./Tables/RegularTables/LightTable";

export default {
  components: {
    LightTable,
    [Dropdown.name]: Dropdown,
    [DropdownItem.name]: DropdownItem,
    [DropdownMenu.name]: DropdownMenu,
    [Table.name]: Table,
    [TableColumn.name]: TableColumn,
  },
  data() {
    return {
      projects,
      users,
      api_key: "ba51dfcd326516d5158d8b26fba8e70e",
      url_base: "https://api.openweathermap.org/data/2.5/weather",
      weather: {},
      code: "",
      temp: 0,
      // 날씨 표현 시 사용될 icon 배열
      icons: [
        "mdi-weather-lightning-rainy", // 2xx : Thunderstorm
        "mdi-weather-rainy", // 3xx : Drizzle
        "mdi-weather-pouring", // 5xx : Rain
        "mdi-weather-snowy", // 6xx : Snow
        "mdi-weather-fog", // 7xx : Atmosphere
        "mdi-weather-sunny", // 800 : Clear
        "mdi-weather-cloudy", // 8xx : Clouds
      ],
    };
  },
  methods: {
    Celsius(kelvin) {
      return kelvin - 273.15;
    },
  },
  mounted() {
    // API 요청 URL (지역 Seoul 고정)

    if (!navigator.geolocation) {
      console.log("위치정보 사용 불가");
    } else {
      navigator.geolocation.getCurrentPosition((position) => {
        let fetchUrl = `${this.url_base}?lat=${position.coords.latitude}&lon=${position.coords.longitude}&appid=${this.api_key}`;
        fetch(fetchUrl)
          .then((response) => {
            return response.json();
          })
          .then((result) => {
            this.weather = result; // 날씨 정보
            this.temp = result.main.temp; // 기온
            this.code = result.weather[0].id.toString(); // 날씨 코드
          });
      });
    }
  },
};
</script>
<style>
.el-table.table-dark {
  background-color: #172b4d;
  color: #f8f9fe;
}

.el-table.table-dark th,
.el-table.table-dark tr {
  background-color: #172b4d;
}

.el-table.table-dark td,
.el-table.table-dark th.is-leaf {
  border-bottom: none;
}
</style>
