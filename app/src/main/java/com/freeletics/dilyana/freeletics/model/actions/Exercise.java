package com.freeletics.dilyana.freeletics.model.actions;

import java.io.Serializable;

/**
 * Created by Dilyana on 3/31/2017.
 */

public class Exercise implements Action, Serializable{

    private static String burpee       = "https://redirector.googlevideo.com/videoplayback?ipbits=0&mm=31&ip=2001%3A19f0%3A7001%3Ad32%3A5400%3Aff%3Afe58%3A19e7&itag=22&ms=au&mt=1492267041&mv=m&id=o-ABJ6tQpkI0MZEok4L0P7y0Q0Wosbh0wnQGprSPqUE2rs&pl=48&source=youtube&sparams=dur%2Cei%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpl%2Cratebypass%2Crequiressl%2Csource%2Cupn%2Cexpire&requiressl=yes&ratebypass=yes&beids=%5B9466591%5D&dur=78.344&mn=sn-a5meknl7&lmt=1471571826948539&ei=ZTDyWPaEPMnQqAG_iYKIDA&upn=MN5OBZGZQF0&expire=1492288710&mime=video%2Fmp4&key=yt6&initcwndbps=696250&signature=1981878CA1704C4082DF0C83E428060C9EF2453C.B1BE64BE6F7259DE2E65DEFB739FF1486666A2C9";
    private static String climber      = "https://redirector.googlevideo.com/videoplayback?ipbits=0&mime=video%2Fmp4&ratebypass=yes&requiressl=yes&ms=au&mt=1492266081&mv=m&dur=66.548&itag=22&key=yt6&mn=sn-n4v7sn7z&id=o-AB6tRMaKPYO_l-Ky7le-Gi6gBM_BQ08iYaQhCivuyH--&expire=1492287753&ei=qCzyWOvVPMXm-QPopLrQCA&ip=2600%3A3c01%3A%3Af03c%3A91ff%3Afe24%3Ab564&lmt=1432796067778186&source=youtube&sparams=dur%2Cei%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpl%2Cratebypass%2Crequiressl%2Csource%2Cupn%2Cexpire&initcwndbps=5975000&pl=33&mm=31&upn=Vc_Jt6WIKVo&signature=94EEB837D10FAE6F6806FFC395076115C82482E6.B96EC3AAAF7B4C81A83C5AE0063EDE19356615B7";
    private static String jump         = "https://redirector.googlevideo.com/videoplayback?signature=7BB792B46E1A7CB19CA1CDBBEEC2519ED796E95C.4E6AE534BAEE2821A1E91F33DFF0F9E052ED19C6&key=yt6&gir=yes&requiressl=yes&dur=46.138&source=youtube&lmt=1448275666803843&ratebypass=yes&expire=1492288867&upn=llGzVtk5WLo&itag=18&mn=sn-ab5l6nzy&mm=31&id=o-AIcz9EzlqGir472qcsXj7Pg-nHCVzUIuuOboEzpQpvbr&clen=2072034&ip=2001%3A19f0%3A5%3A1de%3A5400%3Aff%3Afe4f%3A2207&pl=38&mv=m&mt=1492267160&ms=au&ei=AzHyWJXWI8T_8gSV6is&ipbits=0&initcwndbps=2322500&sparams=clen%2Cdur%2Cei%2Cgir%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpl%2Cratebypass%2Crequiressl%2Csource%2Cupn%2Cexpire&mime=video%2Fmp4";
    private static String squat        = "https://redirector.googlevideo.com/videoplayback?itag=22&ipbits=0&ratebypass=yes&upn=zqlrHfLyD0c&key=yt6&expire=1492289083&id=o-ALUk1h3gcoXb5IatS31UmanENpbAle3iCaER_Oq7hDF4&ms=au&mt=1492267352&mv=m&source=youtube&mm=31&mn=sn-a5meknlz&pl=48&requiressl=yes&ip=2001%3A19f0%3A7001%3Ad32%3A5400%3Aff%3Afe58%3A19e7&mime=video%2Fmp4&ei=2jHyWKOAK4vZqAGzorWIAQ&sparams=dur%2Cei%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpl%2Cratebypass%2Crequiressl%2Csource%2Cupn%2Cexpire&lmt=1471067428312887&dur=59.303&signature=705553C89E4467840A800C54F938D6EDDDA694F4.39D692082240DFAFFBBF1389AB829726CD20E00F&initcwndbps=953750";
    private static String situp        = "https://redirector.googlevideo.com/videoplayback?itag=22&upn=gBoS9pJEcL4&mm=31&signature=7F6AB334BB83E2359D02B58C019A7B86BC1403D1.C0F405FCCA846EBC00776D9BB278F7EB7AED38C1&mn=sn-n4v7sn7y&key=yt6&ip=2600%3A3c01%3A%3Af03c%3A91ff%3Afe24%3Ab564&pl=33&source=youtube&lmt=1471701861567421&dur=68.823&mt=1492267822&mv=m&ei=gjPyWMiGB8T7-gP627SoDg&id=o-AG5opBD8SFW2556uxWEe1NXPS6ubmPkowh9ctLT53Hoi&ms=au&sparams=dur%2Cei%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpl%2Cratebypass%2Crequiressl%2Csource%2Cupn%2Cexpire&initcwndbps=6700000&ratebypass=yes&ipbits=0&mime=video%2Fmp4&requiressl=yes&expire=1492289506";
    private static String pullup       = "https://redirector.googlevideo.com/videoplayback?mime=video%2Fmp4&signature=6C91CBDAB78A97039CABFA1FD4ED9E3C4A0C878A.DFEEE0F974569FC3E7D19579F417F49793DB2243&key=yt6&initcwndbps=2392500&source=youtube&lmt=1471056980129612&ipbits=0&itag=22&expire=1492289729&sparams=dur%2Cei%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpl%2Cratebypass%2Crequiressl%2Csource%2Cupn%2Cexpire&upn=e9jMyx0ls3s&id=o-AEQhvDPvqMFNV4NUXmbBcO68mhXCvVbC3p-eas3VZHgB&requiressl=yes&dur=53.986&mm=31&mn=sn-ab5l6n7y&pl=38&ms=au&mt=1492268061&mv=m&ip=2001%3A19f0%3A5%3A1de%3A5400%3Aff%3Afe4f%3A2207&ei=YTTyWP-6M4XW8gTkj4KYCw&ratebypass=yes";
    private static String crunch       = "https://redirector.googlevideo.com/videoplayback?ratebypass=yes&initcwndbps=1510000&pl=48&requiressl=yes&ipbits=0&key=yt6&ip=2001%3A19f0%3A7001%3Ad32%3A5400%3Aff%3Afe58%3A19e7&ei=iDXyWJebOYTZqAGlzJvwBQ&id=o-ACtAmEKwvFNFBjQ_HrKI-LP2NwgmAEKtoDJcoySIuNds&upn=TqC47rtBIKY&gir=yes&dur=65.619&mm=31&mn=sn-a5meknll&ms=au&mt=1492268002&mv=m&clen=1601876&lmt=1479831996095957&mime=video%2Fmp4&source=youtube&sparams=clen%2Cdur%2Cei%2Cgir%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpl%2Cratebypass%2Crequiressl%2Csource%2Cupn%2Cexpire&expire=1492290025&itag=18&signature=1F2573C20EAE55A624B5EA9CA79A751259E49923.5414BAC241CAC0879BAE3E5D94DD8B014BFAE9E3";
    private static String frogger      = "https://redirector.googlevideo.com/videoplayback?initcwndbps=6517500&dur=39.148&upn=v78OjfXgj-k&key=yt6&ratebypass=yes&ipbits=0&clen=1725471&itag=18&requiressl=yes&mime=video%2Fmp4&ip=2600%3A3c01%3A%3Af03c%3A91ff%3Afe24%3Ab564&mm=31&source=youtube&pl=33&mn=sn-n4v7sn76&id=o-AP3QucULB9XCwmtIrP6rUerzRwLnuzq55RyfmMDQsYYH&ms=au&mt=1492268482&mv=m&expire=1492290162&gir=yes&sparams=clen%2Cdur%2Cei%2Cgir%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpl%2Cratebypass%2Crequiressl%2Csource%2Cupn%2Cexpire&lmt=1434555590728818&ei=EjbyWKqpKMmQ-wPtyZLgDw&signature=3D70659A02D69CDD344F023D3135B0F9FC3D9FBD.C7AF1D0B8408720AEC2E38FAB0F5B7E9AD6A2180";
    private static String pushup       = "https://redirector.googlevideo.com/videoplayback?mv=m&pl=33&mt=1492268302&ms=au&ipbits=0&initcwndbps=6501250&gir=yes&ip=2600%3A3c01%3A%3Af03c%3A91ff%3Afe24%3Ab564&key=yt6&mn=sn-n4v7sn7z&mm=31&id=o-AGU7X2BwVye6nDHe9FRTrcYwPqGhwzQ2zwRHSPX_1NRW&ei=hjbyWLjEGMrc-wPYhqDQBw&dur=43.026&sparams=clen%2Cdur%2Cei%2Cgir%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpl%2Cratebypass%2Crequiressl%2Csource%2Cupn%2Cexpire&requiressl=yes&signature=067663E6D07B22F0F9AF206DF68CA1D7DCB61900.21B3626491C3C99C078B98A1D04F10B5C5F4BD31&expire=1492290278&mime=video%2Fmp4&itag=18&ratebypass=yes&source=youtube&clen=1845845&upn=CmqLS1MA6zo&lmt=1427528267151450";
    private static String standups     = "https://redirector.googlevideo.com/videoplayback?ei=PjfyWL60PIf5ogO_2Z2wBA&ms=au&mt=1492268783&mv=m&pl=19&ip=78.157.200.133&id=o-AGP--ihJEPgBDd-0LyUUPJ4WeH42NJ-N41hoTBP4o7Xq&mm=31&mn=sn-aigllnsd&clen=1948508&sparams=clen%2Cdur%2Cei%2Cgir%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cpl%2Cratebypass%2Crequiressl%2Csource%2Cupn%2Cexpire&initcwndbps=5566250&ipbits=0&lmt=1466008531999697&source=youtube&dur=44.025&requiressl=yes&gir=yes&key=yt6&itag=18&upn=uw2XVnxRG1I&expire=1492290463&ratebypass=yes&mime=video%2Fmp4&signature=E024E4E031CFA278DB950493EE894B713BA2CE04.888CF6C2AA3CF2C474564973225877D1FCABCD50";

    public static String noEquipment = "No Equipment";

    public enum ExerciseName{BURPEES(6, burpee, noEquipment, 10), CLIMBERS(3.5, climber, noEquipment, 10), JUMPS(8, jump, noEquipment, 10), SQUATS(3.5, squat, noEquipment, 10),
        SITUPS(3.5, situp, noEquipment, 10), PULLUPS(5, pullup, "Pullup Bar", 10), CRUNCHES(6, crunch, noEquipment, 10), FROGGERS(5, frogger, noEquipment, 10), PUSHUPS(5, pushup, noEquipment, 10),
        STANDUPS(3, standups, noEquipment, 10);

        private double points;
        private String videoUrl;
        private String equipment;
        private int repetitions;
        private ExerciseName(double points, String videoUrl, String equipment, int repetitions){
            this.points = points;
            this.videoUrl = videoUrl;
            this.equipment = equipment;
            this.repetitions = repetitions;
        }

        public double getPoints() {
            return points;
        }
    }

    private ExerciseName name;

    public Exercise(ExerciseName name) {
        if(name != null){
            this.name = name;
        }
    }

    public ExerciseName getName() {
        return name;
    }

    public int getRepetitions(){
        return this.name.repetitions;
    }

    public double getPoints(){
        return this.name.points;
    }

    public String getVideoUrl(){
        return this.name.videoUrl;
    }

    public String getEquipment() {
        return this.name.equipment;
    }
}
