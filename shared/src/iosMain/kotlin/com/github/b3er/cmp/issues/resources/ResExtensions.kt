package com.github.b3er.cmp.issues.resources

import cnames.structs.CGDataProvider
import cnames.structs.CGFont
import cnames.structs.__CFData
import cnames.structs.__CTFont
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.convert
import org.jetbrains.compose.resources.ExperimentalResourceApi
import platform.CoreFoundation.CFDataCreate
import platform.CoreFoundation.CFRelease
import platform.CoreFoundation.kCFAllocatorDefault
import platform.CoreGraphics.CGDataProviderCreateWithCFData
import platform.CoreGraphics.CGDataProviderRelease
import platform.CoreGraphics.CGFontCreateWithDataProvider
import platform.CoreText.CTFontCreateWithGraphicsFont
import platform.Foundation.CFBridgingRelease
import platform.Foundation.NSData
import platform.Foundation.NSURL
import platform.Foundation.dataWithContentsOfURL
import platform.UIKit.UIFont
import platform.darwin.UInt8Var

/**
 * Create UIFont from file using compose multiplatform resources.
 * @param fileName name of the font file as it stored in resources
 * @param size size of the font
 * @return UIFont
 */
@ExperimentalForeignApi
@ExperimentalResourceApi
fun Res.UIFont(fileName: String, size: Double): UIFont {
    val uri = getUri("font/$fileName")
    val url = NSURL.URLWithString(uri)
    requireNotNull(url) { "Could not url font from $uri" }

    val data = NSData.dataWithContentsOfURL(url)
    requireNotNull(data) { "Could not data font from $uri" }

    @Suppress("UNCHECKED_CAST")
    val cfDataRef: CPointer<__CFData>? = CFDataCreate(
        allocator = kCFAllocatorDefault,
        bytes = data.bytes() as CPointer<UInt8Var>,
        length = data.length.toLong().convert()
    )
    val dataProvider: CPointer<CGDataProvider>? = CGDataProviderCreateWithCFData(cfDataRef)
    val cgFont: CPointer<CGFont>? = CGFontCreateWithDataProvider(dataProvider)
    requireNotNull(cgFont) { "Could not create CGFont from $uri" }

    CGDataProviderRelease(dataProvider)
    CFRelease(cfDataRef)
    val ctFont: CPointer<__CTFont>? = CTFontCreateWithGraphicsFont(
        graphicsFont = cgFont,
        size = size,
        matrix = null,
        attributes = null
    )
    return CFBridgingRelease(ctFont) as UIFont
}
