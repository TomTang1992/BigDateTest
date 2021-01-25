package com.tdf.homeWork;


-- case class Log
/*
    case class Log
 */
        case class Log(commandid: String,
                       houseid: String,
                       gathertime: String,
                       srcip: String,
                       destip: String,
                       srcport: String,
                       destport: String,
                       domainname: String,
                       proxytype: String,
                       proxyid: String,
                       title: String,
                       content: String,
                       url: String,
                       logid: String
)

object Log {
    def apply(line: String): Log = {
        val fields = line.split(",")
                .map(formatField)

        new Log(fields(0), fields(1), fields(2), fields(3),
                fields(4), fields(5), fields(6), fields(7),
                fields(8), fields(9), fields(10), fields(11),
                fields(13), fields(14)
        )
    }

    def formatField(field: String): String = {
        field.replaceAll("<<<!>>>", "")
    }
}