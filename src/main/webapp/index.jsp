<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>Index</title>
</head>

<body>
    <div style="text-align: center">
        <h1>Start a tennis match</h1>
        <form method="get" action="${pageContext.request.contextPath}/new-match">
            <div style="text-align: center"><input type="submit" value="Start new match"></div>
        </form>
    </div>

    </br>

    <div style="text-align: center">
        <form method="get" action="${pageContext.request.contextPath}/matches">
            <label>Search matches by player:
                <input type="text" name="filter_by_player_name" value="" placeholder="name">
            </label>
            <input type="submit" value="Search">
        </form>
    </div>
</body>

</html>