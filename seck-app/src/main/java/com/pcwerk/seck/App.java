package com.pcwerk.seck;

import java.util.ArrayList;
import java.util.HashMap;

import gnu.getopt.Getopt;
import gnu.getopt.LongOpt;

public class App {
  
  protected ArrayList<String> commandStack = new ArrayList<String>();
  protected HashMap<String,String> params = new HashMap<String,String>();
  
  public static void main(String[] argv) { 
    App app = new App();
    app.parseArgs(argv);
    app.showParams();
    app.run();
  }

  private void showParams() {
    System.out.println("[i] commands:");
    for (String command : commandStack) {
      System.out.println("[i]   " + command);
    }
    
    System.out.println("[i] parameters:");
    for (String key : params.keySet() ) {
      System.out.println("[i]   " + key + " => " + params.get(key));
    }
  }

  private void usage() {
    System.out.println("usage: App <command> <required parameters> [options]");
    System.out.println("command(s):");
    System.out.println("crawl             perform crawling action");
    System.out.println("  --root-url=URL  start crawling at URL (required)");
    System.out.println("options:");
    System.out.println("  --tc=#          specify the thread count (default=1)");
    System.out.println("  --depth=#       specify crawling depth (default = 1");
    System.out.println("  --file=FILENAME specify a datafile to save (if not specified, use to stdout)");
  }
  
  private void run() {
    for (String command : commandStack) {
      System.out.println("[i] execute: " + command);
      if (command.equals("crawl")) {
        if (! params.containsKey("root-url")) {
          usage();
          System.exit(0);
        }
        crawl();
      } else if (command.equals("crawl-info")) {
        crawlInfo();
      } else {
        System.out.println("[e]   '" + command + "' => unknown command");
      }
    }
  }
  
  private void crawl() {
    System.out.println("[i]   crawling starts");
    
    // put in your crawling code here -- all parameters are in params
    
    System.out.println("[i]   crawling ends");
  }

  private void crawlInfo() {
    System.out.println("[i]   display information on the crawled data");
    
    // put your crawling information code here -- all parameters are in params
  }

  private void parseArgs(String[] argv) {
    StringBuffer sb = new StringBuffer();

    final String shortopts = "-:hu:c:f:d:;";
    final LongOpt[] longopts = 
    { 
        new LongOpt("help", LongOpt.NO_ARGUMENT, null, 'h'),
        new LongOpt("root-url", LongOpt.REQUIRED_ARGUMENT, sb, 'u'),
        new LongOpt("tc", LongOpt.REQUIRED_ARGUMENT, sb, 'c'),
        new LongOpt("depth", LongOpt.REQUIRED_ARGUMENT, sb, 'd'),
        new LongOpt("file", LongOpt.REQUIRED_ARGUMENT, sb, 'f')
    };

    Getopt g = new Getopt("App", argv, shortopts, longopts);
    g.setOpterr(false);

    int c = 0;

    while ((c = g.getopt()) != -1) {
      String key = longopts[g.getLongind()].getName();
      String val = g.getOptarg();

      switch (c) {
      case 0:
        params.put(key, val);
        break;
      case 1:
        commandStack.add(val);
        break;
      case 2:
        // weirdness occurs!
        break;
      case 'h':
        usage();
        System.exit(0);
        break;
      case 'u':
        params.put("root-url", g.getOptarg());
        break;
      case 'c':
        params.put("tc", g.getOptarg());
        break;
      case 'd':
        params.put("depth", g.getOptarg());
        break;
      case 'f':
        params.put("file", g.getOptarg());
        break;
      case ':':
        System.out.println("You need an argument for option " + (char) g.getOptopt());
        break;
      case '?':
        System.out.println("The option '" + (char) g.getOptopt() + "' is not valid");
        break;
      default:
        System.out.println("getopt() returned " + c);
        break;
      }
    }
    
    /*
    for (int i = g.getOptind(); i < argv.length; i++) {
      commandStack.add(argv[i]);
    }
    */
  }
}
