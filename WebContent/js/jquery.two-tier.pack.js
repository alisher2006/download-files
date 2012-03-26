 
$(document).ready(function () {
$.ajax({
    type: "GET",
    url: "files.json",
    dataType: "json",
    success: function(data) {
        $.each(data.posts, function(i, data) {
            urlParams = {
                id: data.AttachmentId,
                filename: data.FileName
            }
            var query = "redir?attachmentId=" + urlParams["id"] + "&downloadFilename=" + encodeURIComponent(urlParams["filename"]);
            var div_data = "<div class=\"container\"><div class=\"left\"><div id=\"numbers\">" + (i + 1) + "</div></div><div class=\"right\"><li><a href=" + query + ">" + data.FileName + "</a></div><div class=\"calendericon\" id=\"date\" style=\"padding-left:26px;\" >" + data.DateAdded + "</div><div class=\"filesize\">" + data.FileSize + "</div></li></div>  ";
            if (i % 2 == 0) {
                $('.odd-list').append(div_data);
            }
            else {
                $('.even-list').append(div_data);
            }
        });
    },
    error: function(e, xhr) {
        alert("something wrong");
    }
	});
});
$(window).load(function() {
    // Bind click events to links. This will bind a click
    // handler to all current links as well as all new
    // links added to the page.
    $("html").click(function(event) {
        if (event.target.nodeName != 'A') {
            alert("Please click a link");
        }
    });
});

 