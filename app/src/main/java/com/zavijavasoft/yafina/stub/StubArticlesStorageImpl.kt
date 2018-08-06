package com.zavijavasoft.yafina.stub

import com.zavijavasoft.yafina.model.*
import io.reactivex.Completable
import io.reactivex.Single
import java.io.InvalidObjectException


class StubArticlesStorageImpl : ArticlesStorage {
    val list = mutableListOf<ArticleEntity>()

    init {
        list.add(ArticleEntity(ARTICLE_INCOME_TRANSITION_SPECIAL_ID, ArticleType.INCOME, "Скрытая (входящий для переводов между счетами)", ""))
        list.add(ArticleEntity(ARTICLE_OUTCOME_TRANSITION_SPECIAL_ID, ArticleType.OUTCOME, "Скрытая (исходящая для переводов между счетами)", ""))
        list.add(ArticleEntity(1, ArticleType.OUTCOME, "Продукты", "То, что едят"))
        list.add(ArticleEntity(2, ArticleType.OUTCOME, "Одежда", "То, что надевают"))
        list.add(ArticleEntity(3, ArticleType.OUTCOME, "Выплаты по кредиту", "Отдавать свои кровные"))
        list.add(ArticleEntity(4, ArticleType.OUTCOME, "ЖКХ", "За воду и свет"))
        list.add(ArticleEntity(5, ArticleType.INCOME, "Зарплата", "Зарплата -- она и есть зарплата"))
        list.add(ArticleEntity(6, ArticleType.INCOME, "Дивиденды", "Доход от акций и облигаций"))
        list.add(ArticleEntity(7, ArticleType.OUTCOME, "Транспорт", "На проезд"))
        list.add(ArticleEntity(8, ArticleType.OUTCOME, "Прочие расходы", "Не помню куда"))
        list.add(ArticleEntity(9, ArticleType.INCOME, "Прочие доходы", "Не помню откуда"))
    }

    override fun getArticles(): Single<List<ArticleEntity>> {
        return Single.just(list)

    }

    override fun getArticleById(id: Long): Single<ArticleEntity> {
        val item = list.find { it -> it.articleId == id }
        if (item != null) {
            return Single.just(item)
        }
        return Single.error(InvalidObjectException("no such article id"))
    }

    override fun addArticle(articleEntity: ArticleEntity): Completable {
        return Completable.complete()
    }
}