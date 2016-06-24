
<style>

    /* for twemoji */
    .emoji {
        height: 1em;
        vertical-align: middle;
    }

    body {
        background: #444;
        color: #EEE;
        font-family: "DejaVu Sans", Arial, sans-serif;
    }

    #editor {
        color: #000;
        box-shadow: 0 2px 5px #000;
        background: #eee;
        padding: 10px 20px;
        margin: 0 auto;
        max-width: 800px;
    }

    blockquote {
        border-left: 10px solid #ccc;
        background: #FFF;
        margin: 1em;
        padding: .5em 1em;
    }

    code, pre {
        background: #FFF;
        padding: 2px 5px;
        font-size: .87em;
        margin: initial 5px;
        font-family: "DejaVu Sans Mono", "Consolas", "Courier New", monospace;
    }

    table, td, th {
        border-collapse:collapsed;
        border: 1px solid #333;
    }
</style>

<div id="editor" contentEditable="true">
    <p>Input your text here...</p>
</div>

<!-- REQUIRED: MarkdownIME JavaScript -->
<script src="http://build.laobubu.net/MarkdownIME/MarkdownIME.min.js"></script>

<!-- OPTIONAL: twemoji makes emoji beautiful -->
<script src="https://twemoji.maxcdn.com/twemoji.min.js"></script>
<script>
    var editor = document.getElementById('editor');
    MarkdownIME.Enhance(editor);

    //optional: enable Tex Formula support
    var math = new MarkdownIME.Addon.MathAddon();
    MarkdownIME.Renderer.inlineRenderer.addRule(math);
</script>