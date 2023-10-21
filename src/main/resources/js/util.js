function harma(){
    console.log("yoyoyo");
}

function isElementVisible(el) {
    // check all following conditions
    let jqVisible = elJqVisibleCheck(el);
    let hasAriaHidden = elHasAttribute(el, "aria-hidden", "true");
    let anyParentHasAriaHidden = anyParentHasAttribute(el, "aria-hidden", "true");
    return jqVisible && !hasAriaHidden && !anyParentHasAriaHidden;
}

function elJqVisibleCheck(el) {
    return $(el).is(":visible");
}

function elHasAttribute(el, _attr, _attrValue) {
    if ($(el).attr(_attr) != undefined) {
        return $(el).attr(_attr) == _attrValue;
    } else {
        return false;
    }
}

function anyParentHasAttribute(el, _attr, _attrValue) {
    let result = false;
    $(el).parents().each(function(){
        let _actualAttrValue = $(this).attr(_attr);
        if (_actualAttrValue != undefined) {
            if (_actualAttrValue == _attrValue) {
                result = true;
                // breaking loop
                return false;
            }
        }
    })
    return result;
}

function isAppByNameVisible(name){
    let app = $("[data-app='"+name+"']");
    let appEl = app[0];
    return isElementVisible(app);
}