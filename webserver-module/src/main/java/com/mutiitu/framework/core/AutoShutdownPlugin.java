package com.mutiitu.framework.core;

import io.javalin.Javalin;
import io.javalin.plugin.*;

import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.TimeUnit;

public class AutoShutdownPlugin implements Plugin {

    private final List<Path> paths;

    public AutoShutdownPlugin(List<Path> paths) {
        this.paths = paths;
    }

    @Override
    public void apply(Javalin app) {
        FileWatcher fileWatcher;
        try {
            fileWatcher = new FileWatcher(paths, () -> {
                app.stop();
                System.exit(0);
            });
            fileWatcher.start();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

class FileWatcher extends Thread {

    private final List<Path> paths;
    private final WatchService watchService;
    private final Runnable fileChangedHandler;
    private final ConcurrentLinkedDeque<WatchKey> watchKeys = new ConcurrentLinkedDeque<>();
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    public FileWatcher(List<Path> paths, Runnable fileChangedHandler) throws IOException {
        this.paths = paths;
        this.watchService = FileSystems.getDefault().newWatchService();
        this.fileChangedHandler = fileChangedHandler;
    }

    @Override
    public void run() {
        try {
            for (Path path : paths) {
                try {
                    addWatchDirectory(path);
                } catch (NoSuchFileException e) {
                    logger.warn("Not found: " + path);
                }
            }
            logger.debug("Start watching...");
            while (true) {
                watch();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void watch() {
        WatchKey key;
        try {
            key = watchService.poll(300, TimeUnit.MILLISECONDS);
            if (key != null) {
                if (watchKeys.contains(key)) {
                    for (WatchEvent<?> event : key.pollEvents()) {
                        if (event.context() instanceof Path) {
                            fileChangedHandler.run();
                        }
                    }
                }
                key.reset();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private WatchKey registerPath(Path path) throws IOException {
        return path.register(watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);
    }

    private boolean isValidDirectoryToMonitor(File file) {
        return file.isDirectory() && !file.isHidden() && !file.getName().startsWith(".");
    }

    private void addWatchDirectory(Path path) throws IOException {

        logger.debug("Add watch path:" + path.toUri());

        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path p, BasicFileAttributes attr) {
                if (!isValidDirectoryToMonitor(p.toFile())) {
                    return FileVisitResult.SKIP_SUBTREE;
                }
                logger.debug("Add watch path: " + p);
                try {
                    watchKeys.add(registerPath(p));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
