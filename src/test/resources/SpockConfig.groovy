import java.lang.annotation.Annotation

spockReports {

    set 'com.athaydes.spockframework.report.showCodeBlocks': true
    set 'com.athaydes.spockframework.report.outputDir': 'build/spock-reports'
    set 'com.athaydes.spockframework.report.internal.HtmlReportCreator.printThrowableStackTrace': true

}

runner {

    filterStackTrace true

    parallel {
        enabled true
    }

}