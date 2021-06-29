package com.krab1.cucumberstf.cucumber;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventHandler;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestRunFinished;
import io.cucumber.plugin.event.TestRunStarted;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.json.support.Status;
import net.masterthought.cucumber.presentation.PresentationMode;

import java.io.File;
import java.util.Collections;

public class STFEventListener implements ConcurrentEventListener {

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestRunStarted.class, startService());
        publisher.registerHandlerFor(TestRunFinished.class, stopService());
        publisher.registerHandlerFor(TestRunFinished.class, generateReport());
    }

    private EventHandler<TestRunFinished> generateReport() {
        return event -> {
            File reportOutputDir = new File("target");
            Configuration configuration = new Configuration(reportOutputDir, "cucumberProject");
            configuration.addPresentationModes(PresentationMode.RUN_WITH_JENKINS);
            configuration.setNotFailingStatuses(Collections.singleton(Status.SKIPPED));
            configuration.setBuildNumber("1");
            configuration.addClassifications("Platform", "Windows");
            configuration.addClassifications("Browser", "Chrome");
            configuration.addClassifications("Branch", "release/1.0");
            configuration.addPresentationModes(PresentationMode.PARALLEL_TESTING);
            new ReportBuilder(Collections.singletonList("target/report-cucumber.json"), configuration).generateReports();
        };
    }

    private EventHandler<TestRunStarted> startService() {
        return event -> {};
    }

    private EventHandler<TestRunFinished> stopService() {
        return event -> {};
    }
}
