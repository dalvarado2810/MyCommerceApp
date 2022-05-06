package com.example.mycommerceapp.data.model

import java.io.Serializable

data class SearchModel(
    val available_filters: List<AvailableFilter>?,
    val available_sorts: List<AvailableSort>?,
    val country_default_time_zone: String?,
    val filters: List<Filter>?,
    val paging: Paging?,
    val query: String?,
    val results: List<Result>?,
    val site_id: String?,
    val sort: Sort?
)

data class AvailableFilter(
    val id: String?,
    val name: String?,
    val type: String?,
    val values: List<Value>?
)

data class AvailableSort(
    val id: String?,
    val name: String?
)

data class Filter(
    val id: String?,
    val name: String?,
    val type: String?,
    val values: List<ValueX>?
)

data class Paging(
    val limit: Int?,
    val offset: Int?,
    val primary_results: Int?,
    val total: Int?
)

data class Result(
    val accepts_mercadopago: Boolean?,
    val address: Address?,
    val attributes: List<Attribute>?,
    val available_quantity: Int?,
    val buying_mode: String?,
    val catalog_product_id: Any?,
    val category_id: String?,
    val condition: String?,
    val currency_id: String?,
    val differential_pricing: DifferentialPricing?,
    val discounts: Any?,
    val domain_id: String?,
    val id: String?,
    val installments: Installments?,
    val listing_type_id: String?,
    val match_score: Any?,
    val melicoin: Any?,
    val offer_score: Any?,
    val offer_share: Any?,
    val official_store_id: Any?,
    val order_backend: Int?,
    val original_price: Any?,
    val permalink: String?,
    val price: Double?,
    val prices: Prices?,
    val sale_price: Any?,
    val seller: Seller?,
    val seller_address: SellerAddress?,
    val shipping: Shipping?,
    val site_id: String?,
    val sold_quantity: Int?,
    val stop_time: String?,
    val tags: List<String>?,
    val thumbnail: String?,
    val thumbnail_id: String?,
    val title: String?,
    val use_thumbnail_id: Boolean?,
    val winner_item_id: Any?
) : Serializable

data class Sort(
    val id: String?,
    val name: String?
)

data class Value(
    val id: String?,
    val name: String?,
    val results: Int?
)

data class ValueX(
    val id: String?,
    val name: String?,
    val path_from_root: List<PathFromRoot>?
)

data class PathFromRoot(
    val id: String?,
    val name: String?
)

data class Address(
    val city_id: Any?,
    val city_name: String?,
    val state_id: String?,
    val state_name: String?
)

data class Attribute(
    val attribute_group_id: String?,
    val attribute_group_name: String?,
    val id: String?,
    val name: String?,
    val source: Long?,
    val value_id: String?,
    val value_name: String?,
    val value_struct: Any?,
    val values: List<ValueXX>?
)

data class DifferentialPricing(
    val id: Int?
)

data class Installments(
    val amount: Double?,
    val currency_id: String?,
    val quantity: Int?,
    val rate: Double?
)

data class Prices(
    val id: String?,
    val payment_method_prices: List<Any>?,
    val presentation: Presentation?,
    val prices: List<Price>?,
    val purchase_discounts: List<Any>?,
    val reference_prices: List<ReferencePrice>?
)

data class Seller(
    val car_dealer: Boolean?,
    val eshop: Eshop?,
    val id: Int?,
    val permalink: String?,
    val real_estate_agency: Boolean?,
    val registration_date: String?,
    val seller_reputation: SellerReputation?,
    val tags: List<String>?
)

data class SellerAddress(
    val address_line: String?,
    val city: City?,
    val comment: String?,
    val country: Country?,
    val id: String?,
    val latitude: String?,
    val longitude: String?,
    val state: State?,
    val zip_code: String?
)

data class Shipping(
    val free_shipping: Boolean?,
    val logistic_type: String?,
    val mode: String?,
    val store_pick_up: Boolean?,
    val tags: List<String>?
)

data class ValueXX(
    val id: String?,
    val name: String?,
    val source: Long?,
    val struct: Any?
)

data class Presentation(
    val display_currency: String?
)

data class Price(
    val amount: Double?,
    val conditions: Conditions?,
    val currency_id: String?,
    val exchange_rate_context: String?,
    val id: String?,
    val last_updated: String?,
    val metadata: Metadata?,
    val regular_amount: Any?,
    val type: String?
)

data class ReferencePrice(
    val amount: Double?,
    val conditions: ConditionsX?,
    val currency_id: String?,
    val exchange_rate_context: String?,
    val id: String?,
    val last_updated: String?,
    val tags: List<Any>?,
    val type: String?
)

data class Conditions(
    val context_restrictions: List<String>?,
    val eligible: Boolean?,
    val end_time: Any?,
    val start_time: Any?
)

data class Metadata(
    val promotion_id: String?,
    val promotion_type: String?
)

data class ConditionsX(
    val context_restrictions: List<String>?,
    val eligible: Boolean?,
    val end_time: Any?,
    val start_time: Any?
)

data class Eshop(
    val eshop_experience: Int?,
    val eshop_id: Int?,
    val eshop_locations: List<Any>?,
    val eshop_logo_url: String?,
    val eshop_rubro: Any?,
    val eshop_status_id: Int?,
    val nick_name: String?,
    val seller: Int?,
    val site_id: String?
)

data class SellerReputation(
    val level_id: String?,
    val metrics: Metrics?,
    val power_seller_status: String?,
    val protection_end_date: String?,
    val real_level: String?,
    val transactions: Transactions?
)

data class Metrics(
    val cancellations: Cancellations?,
    val claims: Claims?,
    val delayed_handling_time: DelayedHandlingTime?,
    val sales: Sales?
)

data class Transactions(
    val canceled: Int?,
    val completed: Int?,
    val period: String?,
    val ratings: Ratings?,
    val total: Int?
)

data class Cancellations(
    val excluded: Excluded?,
    val period: String?,
    val rate: Double?,
    val value: Int?
)

data class Claims(
    val excluded: ExcludedX?,
    val period: String?,
    val rate: Double?,
    val value: Int?
)

data class DelayedHandlingTime(
    val excluded: ExcludedXX?,
    val period: String?,
    val rate: Double?,
    val value: Int?
)

data class Sales(
    val completed: Int?,
    val period: String?
)

data class Excluded(
    val real_rate: Double?,
    val real_value: Int?
)

data class ExcludedX(
    val real_rate: Double?,
    val real_value: Int?
)

data class ExcludedXX(
    val real_rate: Double?,
    val real_value: Int?
)

data class Ratings(
    val negative: Double?,
    val neutral: Double?,
    val positive: Double?
)

data class City(
    val id: Any?,
    val name: String?
)

data class Country(
    val id: String?,
    val name: String?
)

data class State(
    val id: String?,
    val name: String?
)
