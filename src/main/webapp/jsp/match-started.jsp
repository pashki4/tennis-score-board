<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>New-match</title>
    <link rel="stylesheet" href="/css/style.css">
</head>

<body>
    <fieldset>
        <legend>Enter player names:</legend>
        <form name="newMatch" action="/new-match" method="POST">
            <label for="player1">Player1: <input type="text" id="player1" name="player1" required /></label>
            <br>
            <label for="player2">Player2: <input type="text" id="player2" name="player2" required /></label>
            <br>
            <input type="submit" value="Start the game">
        </form>
    </fieldset>
    <div>
        <a href="/index.jsp">Back</a>
        <br>
        <a href="http://localhost:8080/">localhost</a>
    </div>
</body>

</html>