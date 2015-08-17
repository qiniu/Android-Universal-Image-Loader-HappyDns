package com.qiniu.android;

import android.test.AndroidTestCase;

import com.qiniu.android.dns.DnsManager;
import com.qiniu.android.dns.IResolver;
import com.qiniu.android.dns.NetworkInfo;
import com.qiniu.android.dns.local.AndroidDnsServer;
import com.qiniu.android.dns.local.Resolver;

import junit.framework.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;

/**
 * Created by bailong on 15/8/17.
 */
public class CommonDownloaderTest extends AndroidTestCase {
    public void testDownload() throws IOException {
        IResolver r1 = AndroidDnsServer.defaultResolver();
        IResolver r2 = null;
        try {
            r2 = new Resolver(InetAddress.getByName("223.6.6.6"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        DnsManager dns = new DnsManager(NetworkInfo.normal, new IResolver[]{r1, r2});
        CommonImageDownloader d = new CommonImageDownloader(this.getContext(), 5000, 30000, dns);
        InputStream i = d.getStreamFromNetwork("http://w2.qiniucdn.com/logos/patent_button.png", null);
        byte[] b = new byte[1060];
        int n = i.read(b);
        Assert.assertEquals(n, b.length);
    }
}
