package app.wataso_.watanabe.omoshirorecycle

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class OmoshiroData : Application() {
  override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val realmConfig = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(realmConfig)
    }
}