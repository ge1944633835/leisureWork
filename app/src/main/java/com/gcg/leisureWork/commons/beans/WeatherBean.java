package com.gcg.leisureWork.commons.beans;

import java.util.List;

/**
 * Created by 戈传光 on 2016/7/26.
 * 邮箱 1944633835@qq.com
 */
public class WeatherBean {


    /**
     * status : 0
     * msg : ok
     * result : {"city":"安顺","cityid":"111","citycode":"101260301","date":"2015-12-22","week":"星期二","weather":"多云","temp":"16","temphigh":"18","templow":"9","img":"1","humidity":"55","pressure":"879","windspeed":"14.0","winddirect":"南风","windpower":"2级","updatetime":"2015-12-22 15:37:03","index":[{"iname":"空调指数","ivalue":"较少开启","detail":"您将感到很舒适，一般不需要开启空调。"},{"iname":"运动指数","ivalue":"较适宜","detail":"天气较好，无雨水困扰，较适宜进行各种运动，但因气温较低，在户外运动请注意增减衣物。"}],"aqi":{"so2":"37","so224":"43","no2":"24","no224":"21","co":"0.647","co24":"0.675","o3":"26","o38":"14","o324":"30","pm10":"30","pm1024":"35","pm2_5":"23","pm2_524":"24","iso2":"13","ino2":"13","ico":"7","io3":"9","io38":"7","ipm10":"35","ipm2_5":"35","aqi":"35","primarypollutant":"PM10","quality":"优","timepoint":"2015-12-09 16:00:00","aqiinfo":{"level":"一级","color":"#00e400","affect":"空气质量令人满意，基本无空气污染","measure":"各类人群可正常活动"}},"daily":[{"date":"2015-12-22","week":"星期二","sunrise":"07:39","sunset":"18:09","night":{"weather":"多云","templow":"9","img":"1","winddirect":"无持续风向","windpower":"微风"},"day":{"weather":"多云","temphigh":"18","img":"1","winddirect":"无持续风向","windpower":"微风"}}],"hourly":[{"time":"16:00","weather":"多云","temp":"14","img":"1"},{"time":"17:00","weather":"多云","temp":"13","img":"1"}]}
     */

    private String status;
    private String msg;
    /**
     * city : 安顺
     * cityid : 111
     * citycode : 101260301
     * date : 2015-12-22
     * week : 星期二
     * weather : 多云
     * temp : 16
     * temphigh : 18
     * templow : 9
     * img : 1
     * humidity : 55
     * pressure : 879
     * windspeed : 14.0
     * winddirect : 南风
     * windpower : 2级
     * updatetime : 2015-12-22 15:37:03
     * index : [{"iname":"空调指数","ivalue":"较少开启","detail":"您将感到很舒适，一般不需要开启空调。"},{"iname":"运动指数","ivalue":"较适宜","detail":"天气较好，无雨水困扰，较适宜进行各种运动，但因气温较低，在户外运动请注意增减衣物。"}]
     * aqi : {"so2":"37","so224":"43","no2":"24","no224":"21","co":"0.647","co24":"0.675","o3":"26","o38":"14","o324":"30","pm10":"30","pm1024":"35","pm2_5":"23","pm2_524":"24","iso2":"13","ino2":"13","ico":"7","io3":"9","io38":"7","ipm10":"35","ipm2_5":"35","aqi":"35","primarypollutant":"PM10","quality":"优","timepoint":"2015-12-09 16:00:00","aqiinfo":{"level":"一级","color":"#00e400","affect":"空气质量令人满意，基本无空气污染","measure":"各类人群可正常活动"}}
     * daily : [{"date":"2015-12-22","week":"星期二","sunrise":"07:39","sunset":"18:09","night":{"weather":"多云","templow":"9","img":"1","winddirect":"无持续风向","windpower":"微风"},"day":{"weather":"多云","temphigh":"18","img":"1","winddirect":"无持续风向","windpower":"微风"}}]
     * hourly : [{"time":"16:00","weather":"多云","temp":"14","img":"1"},{"time":"17:00","weather":"多云","temp":"13","img":"1"}]
     */

    private ResultBean result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private String city;
        private String cityid;
        private String citycode;
        private String date;
        private String week;
        private String weather;
        private String temp;
        private String temphigh;
        private String templow;
        private String img;
        private String humidity;
        private String pressure;
        private String windspeed;
        private String winddirect;
        private String windpower;
        private String updatetime;
        /**
         * iname : 空调指数
         * ivalue : 较少开启
         * detail : 您将感到很舒适，一般不需要开启空调。
         */

        private List<IndexBean> index;
        /**
         * date : 2015-12-22
         * week : 星期二
         * sunrise : 07:39
         * sunset : 18:09
         * night : {"weather":"多云","templow":"9","img":"1","winddirect":"无持续风向","windpower":"微风"}
         * day : {"weather":"多云","temphigh":"18","img":"1","winddirect":"无持续风向","windpower":"微风"}
         */

        private List<DailyBean> daily;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCityid() {
            return cityid;
        }

        public void setCityid(String cityid) {
            this.cityid = cityid;
        }

        public String getCitycode() {
            return citycode;
        }

        public void setCitycode(String citycode) {
            this.citycode = citycode;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public String getTemphigh() {
            return temphigh;
        }

        public void setTemphigh(String temphigh) {
            this.temphigh = temphigh;
        }

        public String getTemplow() {
            return templow;
        }

        public void setTemplow(String templow) {
            this.templow = templow;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getPressure() {
            return pressure;
        }

        public void setPressure(String pressure) {
            this.pressure = pressure;
        }

        public String getWindspeed() {
            return windspeed;
        }

        public void setWindspeed(String windspeed) {
            this.windspeed = windspeed;
        }

        public String getWinddirect() {
            return winddirect;
        }

        public void setWinddirect(String winddirect) {
            this.winddirect = winddirect;
        }

        public String getWindpower() {
            return windpower;
        }

        public void setWindpower(String windpower) {
            this.windpower = windpower;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public List<IndexBean> getIndex() {
            return index;
        }

        public void setIndex(List<IndexBean> index) {
            this.index = index;
        }

        public List<DailyBean> getDaily() {
            return daily;
        }

        public void setDaily(List<DailyBean> daily) {
            this.daily = daily;
        }

        public static class IndexBean {
            private String iname;
            private String ivalue;
            private String detail;

            @Override
            public String toString() {
                return "IndexBean{" +
                        "iname='" + iname + '\'' +
                        ", ivalue='" + ivalue + '\'' +
                        ", detail='" + detail + '\'' +
                        '}';
            }

            public String getIname() {
                return iname;
            }

            public void setIname(String iname) {
                this.iname = iname;
            }

            public String getIvalue() {
                return ivalue;
            }

            public void setIvalue(String ivalue) {
                this.ivalue = ivalue;
            }

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }
        }

        public static class DailyBean {
            private String date;
            private String week;
            private String sunrise;
            private String sunset;
            /**
             * weather : 多云
             * templow : 9
             * img : 1
             * winddirect : 无持续风向
             * windpower : 微风
             */

            private NightBean night;
            /**
             * weather : 多云
             * temphigh : 18
             * img : 1
             * winddirect : 无持续风向
             * windpower : 微风
             */

            private DayBean day;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getSunrise() {
                return sunrise;
            }

            public void setSunrise(String sunrise) {
                this.sunrise = sunrise;
            }

            public String getSunset() {
                return sunset;
            }

            public void setSunset(String sunset) {
                this.sunset = sunset;
            }

            public NightBean getNight() {
                return night;
            }

            public void setNight(NightBean night) {
                this.night = night;
            }

            public DayBean getDay() {
                return day;
            }

            public void setDay(DayBean day) {
                this.day = day;
            }

            public static class NightBean {
                private String weather;
                private String templow;
                private String img;
                private String winddirect;
                private String windpower;

                public String getWeather() {
                    return weather;
                }

                public void setWeather(String weather) {
                    this.weather = weather;
                }

                public String getTemplow() {
                    return templow;
                }

                public void setTemplow(String templow) {
                    this.templow = templow;
                }

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }

                public String getWinddirect() {
                    return winddirect;
                }

                public void setWinddirect(String winddirect) {
                    this.winddirect = winddirect;
                }

                public String getWindpower() {
                    return windpower;
                }

                public void setWindpower(String windpower) {
                    this.windpower = windpower;
                }
            }

            public static class DayBean {
                private String weather;
                private String temphigh;
                private String img;
                private String winddirect;
                private String windpower;

                public String getWeather() {
                    return weather;
                }

                public void setWeather(String weather) {
                    this.weather = weather;
                }

                public String getTemphigh() {
                    return temphigh;
                }

                public void setTemphigh(String temphigh) {
                    this.temphigh = temphigh;
                }

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }

                public String getWinddirect() {
                    return winddirect;
                }

                public void setWinddirect(String winddirect) {
                    this.winddirect = winddirect;
                }

                public String getWindpower() {
                    return windpower;
                }

                public void setWindpower(String windpower) {
                    this.windpower = windpower;
                }
            }
        }
    }
}
