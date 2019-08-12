# RoomRxSample

| 種類 | 意味 |
----|---- 
| Observable |  いつもの| 
| Single | onNext+onComplete = onSuccess でonSuccessは一回しか呼べない| 
| Maybe | onSuccessかonErrorかonCompleteのどれかが呼ばれる、または全く呼ばれない| 
| Completable | onErrorかonCompleteのどれかが呼ばれる、または全く呼ばれない| 
