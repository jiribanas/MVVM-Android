package com.jiri.mvvmprototype.plumbing

class Delegate<TS, TA> : Event<TS, TA>
{
    private var invocationList: MutableList<(TS, TA) -> Unit>? = null

    override operator fun plusAssign(m: (TS, TA) -> Unit)
    {
        val list = invocationList ?: mutableListOf<(TS, TA) -> Unit>().apply { invocationList = this }
        list.add(m)
    }

    override operator fun minusAssign(m: (TS, TA) -> Unit)
    {
        val list = invocationList
        if (list != null)
        {
            list.remove(m)
            if (list.isEmpty())
            {
                invocationList = null
            }
        }
    }

    operator fun invoke(source: TS, arg: TA)
    {
        val list = invocationList
        if (list != null)
        {
            for (m in list)
                m(source, arg)
        }
    }

    override fun removeAllHandlers()
    {
        invocationList?.clear()
    }
}