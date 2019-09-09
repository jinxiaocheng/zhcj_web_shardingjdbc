/*!
 * FileInput German Translations
 *
 * This file must be loaded after 'fileinput.js'. Patterns in braces '{}', or
 * any HTML markup tags in the messages must not be converted or translated.
 *
 * @see http://github.com/kartik-v/bootstrap-fileinput
 */
(function ($) {
    "use strict";

    $.fn.fileinput.locales.de = {
        fileSingle: 'Datei',
        filePlural: 'Dateien',
        browseLabel: '请选择图片 &hellip;',
        removeLabel: '批量删除',
        removeTitle: 'Klar ausgewählten Dateien',
        cancelLabel: 'Laden',
        cancelTitle: 'Abbruch laufenden Hochladen',
        uploadLabel: '上传',
        uploadTitle: 'Hochladen ausgewählten Dateien',
        msgSizeTooLarge: 'Datei "{name}" (<b>{size} KB</b>) überschreitet maximal zulässige Upload-Größe von <b>{maxSize} KB</b>. Bitte versuchen Sie Ihr Hochladen!',
        msgFilesTooLess: 'Sie müssen mindestens <b>{n}</b> {files} zum Hochladen auswählen. Bitte versuchen Sie Ihr Hochladen!',
        msgFilesTooMany: '选择上传的文件数量({n}) 超过允许的最大数值{m}！',
        msgFileNotFound: 'Datei "{name}" wurde nicht gefunden!',
        msgFileSecured: 'Sicherheitseinschränkungen verhindern Lesen der Datei "{name}".',
        msgFileNotReadable: 'Datei "{name}" ist nicht lesbar.',
        msgFilePreviewAborted: 'Dateivorschau abgebrochen für "{name}".',
        msgFilePreviewError: 'Beim Lesen der Datei "{name}" ein Fehler aufgetreten.',
        msgInvalidFileType: 'Ungültiger Typ für Datei "{name}". Nur "{types}" Dateien werden unterstützt.',
        msgInvalidFileExtension: 'Ungültige Erweiterung für Datei "{name}". Nur "{extensions}" Dateien werden unterstützt.',
        msgValidationError: '上传文件格式错误',
        msgLoading: 'Wird Geladen Datei {index} von {files} &hellip;',
        msgProgress: 'Wird Geladen Datei {index} von {files} - {name} - {percent}% fertiggestellt.',
        msgSelected: '已选择{n}张图片',
        msgFoldersNotAllowed: 'Drag & Drop Dateien nur! Sprungen {n} gesunken Ordner.',
        dropZoneTitle: '可拖入图片 &hellip;'
    };

    $.extend($.fn.fileinput.defaults, $.fn.fileinput.locales.de);
})(window.jQuery);
