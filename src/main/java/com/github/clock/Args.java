package com.github.clock;

import java.time.ZoneId;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name="clock", versionProvider=VersionProvider.class, description="Sample project for analog clock")
public class Args {
    @Option(names= {"-t", "--timezone"}, description="Specifies time zone.  Default is local time.", paramLabel="TIMEZONE")
    private String timeZone;

    @Option(names={ "-h", "--help"}, description="print this message.")
    private boolean showHelp = false;

    @Option(names={ "-H", "--more-help"}, description="print detail help message.")
    private boolean showMoreHelp = false;

    @Option(names = {"-v", "--version"}, description="show version and quit.")
    private boolean showVersion = false;

    @Option(names={"-d", "--debug"}, description="debug mode.")
    private boolean debugMode = false;

    public boolean isRunningMode(){
        return !isShowVersion() && !isShowHelp();
    }

    public boolean isShowHelp(){
        return showHelp || showMoreHelp;
    }

    public boolean isShowVersion(){
        return showVersion;
    }

    public boolean isDebugMode(){
        return debugMode;
    }

    public ZoneId getZoneId(){
        ZoneId id = ZoneId.systemDefault();
        if(timeZone != null)
            id = ZoneId.of(timeZone);
        return id;
    }

    public void showHelp(CommandLine commandline){
        commandline.usage(System.out);
        if(showMoreHelp){
            System.out.println("Available TimeZones");
            ZoneId.getAvailableZoneIds().stream()
                .forEach(id -> System.out.printf("\t%s%n", id));
        }
    }
}
