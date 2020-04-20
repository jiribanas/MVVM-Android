package com.jiri.mvvmprototype.plumbing

interface Event<out TS, out TA>
{
    operator fun plusAssign(m: (TS, TA) -> Unit)
    operator fun minusAssign(m: (TS, TA) -> Unit)
    fun removeAllHandlers()
}