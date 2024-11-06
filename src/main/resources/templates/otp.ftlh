<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>OTP page</title>
    <style>
        p {
            text-align: center;
            color: purple;
            font-weight: bold;
            font-size: 50px;
        }
    </style>
</head>
<body>
<p>${otp}</p>

<table border="1" cellspacing="0" cellpadding="1">
    <tr class="tableHeader">
        <td>Rep Number</td>
        <td>Order Number</td>
        <td>IO Guid</td>
        <td>Last Update Date</td>
        <td>Last Updated By</td>
    </tr>
    <#foreach order in orders>
        <tr class="tableBody">
            <td>${order.salesrepNumber}</td>
            <td>${order.orderNumber}</td>
            <td>${order.ioGuid}</td>
            <td>${order.lastUpdateDate}</td>
            <td>${order.lastUpdatedByUser}</td>
        </tr>
    </#foreach>
</table>
</body>
</html>