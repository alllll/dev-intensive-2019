package ru.skillbranch.devintensive.extensions

fun String.truncate(count:Int=16):String{
    var result:String="";
    result = this.trimEnd()
    if(count>=result.length)
        result = this
    else
    {
        result = result.substring(0, count)
        result = result.trimEnd()
        result = result.plus("...")
    }
    return result;
}

fun String.stripHtml():String{
    return this
        .replace(Regex("<[^>]*>"), "") // strip html tags
        .replace(Regex("\\s+"), " ") // trim spaces
        .replace(Regex("&#34;|&quot;"), "\"") // Quotation Mark
        .replace(Regex("&#38;|&amp;"), "&")  // Ampersand
        .replace(Regex("&#39;|&apos;"), "'")  // Apostrophe
        .replace(Regex("&#47;|&frasl;"), "/")    // Slash
        .replace(Regex("&#60;|&lt;"), "<")   // Less Than Sign
        .replace(Regex("&#62;|&gt;"), ">")   // Greater Than Sign
        .replace(Regex("&#130;|&sbquo;"), "‚")   // Single Low-9 Quote
        .replace(Regex("&#132;|&bdquo;"), "„")   // Double Low-9 Quote
        .replace(Regex("&#134;|&dagger;"), "†")  // Dagger
        .replace(Regex("&#135;|&Dagger;"), "‡")  // Double Dagger
        .replace(Regex("&#137;|&permil;"), "‰")  // Per Mill Sign
        .replace(Regex("&#139;|&lsaquo;"), "‹")  // Single Left Angle Quote
        .replace(Regex("&#145;|&lsquo;"), "‘")   // Left Single Quote
        .replace(Regex("&#146;|&rsquo;"), "’")   // Right Single Quote
        .replace(Regex("&#147;|&ldquo;"), "“")   // Left Double Quote
        .replace(Regex("&#148;|&rdquo;"), "”")   // Right Double Quote
        .replace(Regex("&#153;|&trade;"), "™")   // Trademark Symbol
        .replace(Regex("&#155;|&rsaquo;"), "›")  // Single Right Angle Quote
        .trim()
}