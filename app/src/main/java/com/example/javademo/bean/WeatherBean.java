package com.example.javademo.bean;

public class WeatherBean {
    /**
     * resultcode : 200
     * reason : successed!
     * result : {"sk":{"temp":"12","wind_direction":"西北风","wind_strength":"2级","humidity":"53%","time":"15:15"},"today":{"temperature":"8℃~12℃","weather":"小雨","weather_id":{"fa":"07","fb":"07"},"wind":"东北风微风","week":"星期二","city":"上海","date_y":"2020年03月03日","dressing_index":"较冷","dressing_advice":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。","uv_index":"最弱","comfort_index":"","wash_index":"不宜","travel_index":"较不宜","exercise_index":"较不宜","drying_index":""},"future":{"day_20200303":{"temperature":"8℃~12℃","weather":"小雨","weather_id":{"fa":"07","fb":"07"},"wind":"东北风微风","week":"星期二","date":"20200303"},"day_20200304":{"temperature":"6℃~12℃","weather":"阴转多云","weather_id":{"fa":"02","fb":"01"},"wind":"东北风4-5级","week":"星期三","date":"20200304"},"day_20200305":{"temperature":"5℃~10℃","weather":"晴转阴","weather_id":{"fa":"00","fb":"02"},"wind":"东北风3-5级","week":"星期四","date":"20200305"},"day_20200306":{"temperature":"9℃~12℃","weather":"阴转小雨","weather_id":{"fa":"02","fb":"07"},"wind":"东南风3-5级","week":"星期五","date":"20200306"},"day_20200307":{"temperature":"9℃~16℃","weather":"阴","weather_id":{"fa":"02","fb":"02"},"wind":"东北风微风","week":"星期六","date":"20200307"},"day_20200308":{"temperature":"9℃~12℃","weather":"阴转小雨","weather_id":{"fa":"02","fb":"07"},"wind":"东南风3-5级","week":"星期日","date":"20200308"},"day_20200309":{"temperature":"5℃~10℃","weather":"晴转阴","weather_id":{"fa":"00","fb":"02"},"wind":"东北风3-5级","week":"星期一","date":"20200309"}}}
     * error_code : 0
     */

    private String resultcode;
    private String reason;
    private ResultBean result;
    private int error_code;

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        /**
         * sk : {"temp":"12","wind_direction":"西北风","wind_strength":"2级","humidity":"53%","time":"15:15"}
         * today : {"temperature":"8℃~12℃","weather":"小雨","weather_id":{"fa":"07","fb":"07"},"wind":"东北风微风","week":"星期二","city":"上海","date_y":"2020年03月03日","dressing_index":"较冷","dressing_advice":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。","uv_index":"最弱","comfort_index":"","wash_index":"不宜","travel_index":"较不宜","exercise_index":"较不宜","drying_index":""}
         * future : {"day_20200303":{"temperature":"8℃~12℃","weather":"小雨","weather_id":{"fa":"07","fb":"07"},"wind":"东北风微风","week":"星期二","date":"20200303"},"day_20200304":{"temperature":"6℃~12℃","weather":"阴转多云","weather_id":{"fa":"02","fb":"01"},"wind":"东北风4-5级","week":"星期三","date":"20200304"},"day_20200305":{"temperature":"5℃~10℃","weather":"晴转阴","weather_id":{"fa":"00","fb":"02"},"wind":"东北风3-5级","week":"星期四","date":"20200305"},"day_20200306":{"temperature":"9℃~12℃","weather":"阴转小雨","weather_id":{"fa":"02","fb":"07"},"wind":"东南风3-5级","week":"星期五","date":"20200306"},"day_20200307":{"temperature":"9℃~16℃","weather":"阴","weather_id":{"fa":"02","fb":"02"},"wind":"东北风微风","week":"星期六","date":"20200307"},"day_20200308":{"temperature":"9℃~12℃","weather":"阴转小雨","weather_id":{"fa":"02","fb":"07"},"wind":"东南风3-5级","week":"星期日","date":"20200308"},"day_20200309":{"temperature":"5℃~10℃","weather":"晴转阴","weather_id":{"fa":"00","fb":"02"},"wind":"东北风3-5级","week":"星期一","date":"20200309"}}
         */

        private SkBean sk;
        private TodayBean today;
        private FutureBean future;

        public SkBean getSk() {
            return sk;
        }

        public void setSk(SkBean sk) {
            this.sk = sk;
        }

        public TodayBean getToday() {
            return today;
        }

        public void setToday(TodayBean today) {
            this.today = today;
        }

        public FutureBean getFuture() {
            return future;
        }

        public void setFuture(FutureBean future) {
            this.future = future;
        }

        public static class SkBean {
            /**
             * temp : 12
             * wind_direction : 西北风
             * wind_strength : 2级
             * humidity : 53%
             * time : 15:15
             */

            private String temp;
            private String wind_direction;
            private String wind_strength;
            private String humidity;
            private String time;

            public String getTemp() {
                return temp;
            }

            public void setTemp(String temp) {
                this.temp = temp;
            }

            public String getWind_direction() {
                return wind_direction;
            }

            public void setWind_direction(String wind_direction) {
                this.wind_direction = wind_direction;
            }

            public String getWind_strength() {
                return wind_strength;
            }

            public void setWind_strength(String wind_strength) {
                this.wind_strength = wind_strength;
            }

            public String getHumidity() {
                return humidity;
            }

            public void setHumidity(String humidity) {
                this.humidity = humidity;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }
        }

        public static class TodayBean {
            /**
             * temperature : 8℃~12℃
             * weather : 小雨
             * weather_id : {"fa":"07","fb":"07"}
             * wind : 东北风微风
             * week : 星期二
             * city : 上海
             * date_y : 2020年03月03日
             * dressing_index : 较冷
             * dressing_advice : 建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。
             * uv_index : 最弱
             * comfort_index :
             * wash_index : 不宜
             * travel_index : 较不宜
             * exercise_index : 较不宜
             * drying_index :
             */

            private String temperature;
            private String weather;
            private WeatherIdBean weather_id;
            private String wind;
            private String week;
            private String city;
            private String date_y;
            private String dressing_index;
            private String dressing_advice;
            private String uv_index;
            private String comfort_index;
            private String wash_index;
            private String travel_index;
            private String exercise_index;
            private String drying_index;

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public WeatherIdBean getWeather_id() {
                return weather_id;
            }

            public void setWeather_id(WeatherIdBean weather_id) {
                this.weather_id = weather_id;
            }

            public String getWind() {
                return wind;
            }

            public void setWind(String wind) {
                this.wind = wind;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getDate_y() {
                return date_y;
            }

            public void setDate_y(String date_y) {
                this.date_y = date_y;
            }

            public String getDressing_index() {
                return dressing_index;
            }

            public void setDressing_index(String dressing_index) {
                this.dressing_index = dressing_index;
            }

            public String getDressing_advice() {
                return dressing_advice;
            }

            public void setDressing_advice(String dressing_advice) {
                this.dressing_advice = dressing_advice;
            }

            public String getUv_index() {
                return uv_index;
            }

            public void setUv_index(String uv_index) {
                this.uv_index = uv_index;
            }

            public String getComfort_index() {
                return comfort_index;
            }

            public void setComfort_index(String comfort_index) {
                this.comfort_index = comfort_index;
            }

            public String getWash_index() {
                return wash_index;
            }

            public void setWash_index(String wash_index) {
                this.wash_index = wash_index;
            }

            public String getTravel_index() {
                return travel_index;
            }

            public void setTravel_index(String travel_index) {
                this.travel_index = travel_index;
            }

            public String getExercise_index() {
                return exercise_index;
            }

            public void setExercise_index(String exercise_index) {
                this.exercise_index = exercise_index;
            }

            public String getDrying_index() {
                return drying_index;
            }

            public void setDrying_index(String drying_index) {
                this.drying_index = drying_index;
            }

            public static class WeatherIdBean {
                /**
                 * fa : 07
                 * fb : 07
                 */

                private String fa;
                private String fb;

                public String getFa() {
                    return fa;
                }

                public void setFa(String fa) {
                    this.fa = fa;
                }

                public String getFb() {
                    return fb;
                }

                public void setFb(String fb) {
                    this.fb = fb;
                }
            }
        }

        public static class FutureBean {
            /**
             * day_20200303 : {"temperature":"8℃~12℃","weather":"小雨","weather_id":{"fa":"07","fb":"07"},"wind":"东北风微风","week":"星期二","date":"20200303"}
             * day_20200304 : {"temperature":"6℃~12℃","weather":"阴转多云","weather_id":{"fa":"02","fb":"01"},"wind":"东北风4-5级","week":"星期三","date":"20200304"}
             * day_20200305 : {"temperature":"5℃~10℃","weather":"晴转阴","weather_id":{"fa":"00","fb":"02"},"wind":"东北风3-5级","week":"星期四","date":"20200305"}
             * day_20200306 : {"temperature":"9℃~12℃","weather":"阴转小雨","weather_id":{"fa":"02","fb":"07"},"wind":"东南风3-5级","week":"星期五","date":"20200306"}
             * day_20200307 : {"temperature":"9℃~16℃","weather":"阴","weather_id":{"fa":"02","fb":"02"},"wind":"东北风微风","week":"星期六","date":"20200307"}
             * day_20200308 : {"temperature":"9℃~12℃","weather":"阴转小雨","weather_id":{"fa":"02","fb":"07"},"wind":"东南风3-5级","week":"星期日","date":"20200308"}
             * day_20200309 : {"temperature":"5℃~10℃","weather":"晴转阴","weather_id":{"fa":"00","fb":"02"},"wind":"东北风3-5级","week":"星期一","date":"20200309"}
             */

            private Day20200303Bean day_20200303;
            private Day20200304Bean day_20200304;
            private Day20200305Bean day_20200305;
            private Day20200306Bean day_20200306;
            private Day20200307Bean day_20200307;
            private Day20200308Bean day_20200308;
            private Day20200309Bean day_20200309;

            public Day20200303Bean getDay_20200303() {
                return day_20200303;
            }

            public void setDay_20200303(Day20200303Bean day_20200303) {
                this.day_20200303 = day_20200303;
            }

            public Day20200304Bean getDay_20200304() {
                return day_20200304;
            }

            public void setDay_20200304(Day20200304Bean day_20200304) {
                this.day_20200304 = day_20200304;
            }

            public Day20200305Bean getDay_20200305() {
                return day_20200305;
            }

            public void setDay_20200305(Day20200305Bean day_20200305) {
                this.day_20200305 = day_20200305;
            }

            public Day20200306Bean getDay_20200306() {
                return day_20200306;
            }

            public void setDay_20200306(Day20200306Bean day_20200306) {
                this.day_20200306 = day_20200306;
            }

            public Day20200307Bean getDay_20200307() {
                return day_20200307;
            }

            public void setDay_20200307(Day20200307Bean day_20200307) {
                this.day_20200307 = day_20200307;
            }

            public Day20200308Bean getDay_20200308() {
                return day_20200308;
            }

            public void setDay_20200308(Day20200308Bean day_20200308) {
                this.day_20200308 = day_20200308;
            }

            public Day20200309Bean getDay_20200309() {
                return day_20200309;
            }

            public void setDay_20200309(Day20200309Bean day_20200309) {
                this.day_20200309 = day_20200309;
            }

            public static class Day20200303Bean {
                /**
                 * temperature : 8℃~12℃
                 * weather : 小雨
                 * weather_id : {"fa":"07","fb":"07"}
                 * wind : 东北风微风
                 * week : 星期二
                 * date : 20200303
                 */

                private String temperature;
                private String weather;
                private WeatherIdBeanX weather_id;
                private String wind;
                private String week;
                private String date;

                public String getTemperature() {
                    return temperature;
                }

                public void setTemperature(String temperature) {
                    this.temperature = temperature;
                }

                public String getWeather() {
                    return weather;
                }

                public void setWeather(String weather) {
                    this.weather = weather;
                }

                public WeatherIdBeanX getWeather_id() {
                    return weather_id;
                }

                public void setWeather_id(WeatherIdBeanX weather_id) {
                    this.weather_id = weather_id;
                }

                public String getWind() {
                    return wind;
                }

                public void setWind(String wind) {
                    this.wind = wind;
                }

                public String getWeek() {
                    return week;
                }

                public void setWeek(String week) {
                    this.week = week;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public static class WeatherIdBeanX {
                    /**
                     * fa : 07
                     * fb : 07
                     */

                    private String fa;
                    private String fb;

                    public String getFa() {
                        return fa;
                    }

                    public void setFa(String fa) {
                        this.fa = fa;
                    }

                    public String getFb() {
                        return fb;
                    }

                    public void setFb(String fb) {
                        this.fb = fb;
                    }
                }
            }

            public static class Day20200304Bean {
                /**
                 * temperature : 6℃~12℃
                 * weather : 阴转多云
                 * weather_id : {"fa":"02","fb":"01"}
                 * wind : 东北风4-5级
                 * week : 星期三
                 * date : 20200304
                 */

                private String temperature;
                private String weather;
                private WeatherIdBeanXX weather_id;
                private String wind;
                private String week;
                private String date;

                public String getTemperature() {
                    return temperature;
                }

                public void setTemperature(String temperature) {
                    this.temperature = temperature;
                }

                public String getWeather() {
                    return weather;
                }

                public void setWeather(String weather) {
                    this.weather = weather;
                }

                public WeatherIdBeanXX getWeather_id() {
                    return weather_id;
                }

                public void setWeather_id(WeatherIdBeanXX weather_id) {
                    this.weather_id = weather_id;
                }

                public String getWind() {
                    return wind;
                }

                public void setWind(String wind) {
                    this.wind = wind;
                }

                public String getWeek() {
                    return week;
                }

                public void setWeek(String week) {
                    this.week = week;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public static class WeatherIdBeanXX {
                    /**
                     * fa : 02
                     * fb : 01
                     */

                    private String fa;
                    private String fb;

                    public String getFa() {
                        return fa;
                    }

                    public void setFa(String fa) {
                        this.fa = fa;
                    }

                    public String getFb() {
                        return fb;
                    }

                    public void setFb(String fb) {
                        this.fb = fb;
                    }
                }
            }

            public static class Day20200305Bean {
                /**
                 * temperature : 5℃~10℃
                 * weather : 晴转阴
                 * weather_id : {"fa":"00","fb":"02"}
                 * wind : 东北风3-5级
                 * week : 星期四
                 * date : 20200305
                 */

                private String temperature;
                private String weather;
                private WeatherIdBeanXXX weather_id;
                private String wind;
                private String week;
                private String date;

                public String getTemperature() {
                    return temperature;
                }

                public void setTemperature(String temperature) {
                    this.temperature = temperature;
                }

                public String getWeather() {
                    return weather;
                }

                public void setWeather(String weather) {
                    this.weather = weather;
                }

                public WeatherIdBeanXXX getWeather_id() {
                    return weather_id;
                }

                public void setWeather_id(WeatherIdBeanXXX weather_id) {
                    this.weather_id = weather_id;
                }

                public String getWind() {
                    return wind;
                }

                public void setWind(String wind) {
                    this.wind = wind;
                }

                public String getWeek() {
                    return week;
                }

                public void setWeek(String week) {
                    this.week = week;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public static class WeatherIdBeanXXX {
                    /**
                     * fa : 00
                     * fb : 02
                     */

                    private String fa;
                    private String fb;

                    public String getFa() {
                        return fa;
                    }

                    public void setFa(String fa) {
                        this.fa = fa;
                    }

                    public String getFb() {
                        return fb;
                    }

                    public void setFb(String fb) {
                        this.fb = fb;
                    }
                }
            }

            public static class Day20200306Bean {
                /**
                 * temperature : 9℃~12℃
                 * weather : 阴转小雨
                 * weather_id : {"fa":"02","fb":"07"}
                 * wind : 东南风3-5级
                 * week : 星期五
                 * date : 20200306
                 */

                private String temperature;
                private String weather;
                private WeatherIdBeanXXXX weather_id;
                private String wind;
                private String week;
                private String date;

                public String getTemperature() {
                    return temperature;
                }

                public void setTemperature(String temperature) {
                    this.temperature = temperature;
                }

                public String getWeather() {
                    return weather;
                }

                public void setWeather(String weather) {
                    this.weather = weather;
                }

                public WeatherIdBeanXXXX getWeather_id() {
                    return weather_id;
                }

                public void setWeather_id(WeatherIdBeanXXXX weather_id) {
                    this.weather_id = weather_id;
                }

                public String getWind() {
                    return wind;
                }

                public void setWind(String wind) {
                    this.wind = wind;
                }

                public String getWeek() {
                    return week;
                }

                public void setWeek(String week) {
                    this.week = week;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public static class WeatherIdBeanXXXX {
                    /**
                     * fa : 02
                     * fb : 07
                     */

                    private String fa;
                    private String fb;

                    public String getFa() {
                        return fa;
                    }

                    public void setFa(String fa) {
                        this.fa = fa;
                    }

                    public String getFb() {
                        return fb;
                    }

                    public void setFb(String fb) {
                        this.fb = fb;
                    }
                }
            }

            public static class Day20200307Bean {
                /**
                 * temperature : 9℃~16℃
                 * weather : 阴
                 * weather_id : {"fa":"02","fb":"02"}
                 * wind : 东北风微风
                 * week : 星期六
                 * date : 20200307
                 */

                private String temperature;
                private String weather;
                private WeatherIdBeanXXXXX weather_id;
                private String wind;
                private String week;
                private String date;

                public String getTemperature() {
                    return temperature;
                }

                public void setTemperature(String temperature) {
                    this.temperature = temperature;
                }

                public String getWeather() {
                    return weather;
                }

                public void setWeather(String weather) {
                    this.weather = weather;
                }

                public WeatherIdBeanXXXXX getWeather_id() {
                    return weather_id;
                }

                public void setWeather_id(WeatherIdBeanXXXXX weather_id) {
                    this.weather_id = weather_id;
                }

                public String getWind() {
                    return wind;
                }

                public void setWind(String wind) {
                    this.wind = wind;
                }

                public String getWeek() {
                    return week;
                }

                public void setWeek(String week) {
                    this.week = week;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public static class WeatherIdBeanXXXXX {
                    /**
                     * fa : 02
                     * fb : 02
                     */

                    private String fa;
                    private String fb;

                    public String getFa() {
                        return fa;
                    }

                    public void setFa(String fa) {
                        this.fa = fa;
                    }

                    public String getFb() {
                        return fb;
                    }

                    public void setFb(String fb) {
                        this.fb = fb;
                    }
                }
            }

            public static class Day20200308Bean {
                /**
                 * temperature : 9℃~12℃
                 * weather : 阴转小雨
                 * weather_id : {"fa":"02","fb":"07"}
                 * wind : 东南风3-5级
                 * week : 星期日
                 * date : 20200308
                 */

                private String temperature;
                private String weather;
                private WeatherIdBeanXXXXXX weather_id;
                private String wind;
                private String week;
                private String date;

                public String getTemperature() {
                    return temperature;
                }

                public void setTemperature(String temperature) {
                    this.temperature = temperature;
                }

                public String getWeather() {
                    return weather;
                }

                public void setWeather(String weather) {
                    this.weather = weather;
                }

                public WeatherIdBeanXXXXXX getWeather_id() {
                    return weather_id;
                }

                public void setWeather_id(WeatherIdBeanXXXXXX weather_id) {
                    this.weather_id = weather_id;
                }

                public String getWind() {
                    return wind;
                }

                public void setWind(String wind) {
                    this.wind = wind;
                }

                public String getWeek() {
                    return week;
                }

                public void setWeek(String week) {
                    this.week = week;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public static class WeatherIdBeanXXXXXX {
                    /**
                     * fa : 02
                     * fb : 07
                     */

                    private String fa;
                    private String fb;

                    public String getFa() {
                        return fa;
                    }

                    public void setFa(String fa) {
                        this.fa = fa;
                    }

                    public String getFb() {
                        return fb;
                    }

                    public void setFb(String fb) {
                        this.fb = fb;
                    }
                }
            }

            public static class Day20200309Bean {
                /**
                 * temperature : 5℃~10℃
                 * weather : 晴转阴
                 * weather_id : {"fa":"00","fb":"02"}
                 * wind : 东北风3-5级
                 * week : 星期一
                 * date : 20200309
                 */

                private String temperature;
                private String weather;
                private WeatherIdBeanXXXXXXX weather_id;
                private String wind;
                private String week;
                private String date;

                public String getTemperature() {
                    return temperature;
                }

                public void setTemperature(String temperature) {
                    this.temperature = temperature;
                }

                public String getWeather() {
                    return weather;
                }

                public void setWeather(String weather) {
                    this.weather = weather;
                }

                public WeatherIdBeanXXXXXXX getWeather_id() {
                    return weather_id;
                }

                public void setWeather_id(WeatherIdBeanXXXXXXX weather_id) {
                    this.weather_id = weather_id;
                }

                public String getWind() {
                    return wind;
                }

                public void setWind(String wind) {
                    this.wind = wind;
                }

                public String getWeek() {
                    return week;
                }

                public void setWeek(String week) {
                    this.week = week;
                }

                public String getDate() {
                    return date;
                }

                public void setDate(String date) {
                    this.date = date;
                }

                public static class WeatherIdBeanXXXXXXX {
                    /**
                     * fa : 00
                     * fb : 02
                     */

                    private String fa;
                    private String fb;

                    public String getFa() {
                        return fa;
                    }

                    public void setFa(String fa) {
                        this.fa = fa;
                    }

                    public String getFb() {
                        return fb;
                    }

                    public void setFb(String fb) {
                        this.fb = fb;
                    }
                }
            }
        }
    }
}
