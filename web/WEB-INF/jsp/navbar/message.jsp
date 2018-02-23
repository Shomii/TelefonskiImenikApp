<c:if test="${not empty error}">
    <div class="error">${error}</div>
</c:if>
<c:if test="${not empty errorEnabled}">
    <div class="error">${errorEnabled}</div>
</c:if>
<c:if test="${not empty msg}">
    <div class="msg">${msg}</div>
</c:if>
<c:if test="${not empty mailMsg}">
    <div class="msg">${mailMsg}</div>
</c:if>
<c:if test="${not empty mailMsgForgotPass}">
    <div class="msg">${mailMsgForgotPass}</div>
</c:if>
<c:if test="${not empty mailMsgActivatingOk}">
    <div class="msg">${mailMsgActivatingOk}</div>
</c:if>

